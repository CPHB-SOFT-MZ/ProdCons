/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikkel
 */
public class Producer implements Runnable{

    private final BlockingQueue takeQueue;
    private final BlockingQueue giveQueue;
    private final Consumer consumer;
    Producer(BlockingQueue<Long> takeQueue, BlockingQueue<Long> giveQueue, Consumer consumer) {
        this.takeQueue = takeQueue;
        this.giveQueue = giveQueue;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.addProducer(this);
        Long l;
        while((l = (Long)takeQueue.poll()) != null){
            System.out.println("Taken: " + l);
            try {
                long fib = fib(l);
                System.out.println("Fib: " + fib);
                giveQueue.put(fib);
                System.out.println("Calculation next");
            } catch (InterruptedException ex) {
                
            }
        }
        
        consumer.deregisterProducer(this);
        Thread.currentThread().interrupt();
        
    }
    
    private long fib(long n){
        if((n == 0) || (n == 1)){
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    
    
    
}

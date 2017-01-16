/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikkel
 */
public class Consumer implements Runnable{

    List<Producer> producers = new ArrayList();
    BlockingQueue<Long> calcQueue;
    long sum = 0;
    
    Consumer(BlockingQueue<Long> calcQueue) {
        this.calcQueue = calcQueue;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void addProducer(Producer producer) {
        this.producers.add(producer);
    }
    
    public void deregisterProducer(Producer producer){
        this.producers.remove(producer);
    }
    
    

    @Override
    public void run() {
        while(true){
            if(producers.isEmpty() && calcQueue.isEmpty()){
                    System.out.println("Sum of everything is: " + sum);
                    break;
            }
            try {
                Long l = this.calcQueue.take();
                System.out.println("Fib value: " + l.longValue());
                this.sum += l;
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}

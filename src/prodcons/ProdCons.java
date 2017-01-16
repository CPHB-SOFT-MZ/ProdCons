/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Mikkel
 */
public class ProdCons {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        startTheDamnEngine(2);
    }
    
    private static void startTheDamnEngine(int producerThreads) throws InterruptedException{
        long[] longs = {4,5,8,12,21,22,34,35,36,37,42,43,44,45,46};
        BlockingQueue<Long> queue = new ArrayBlockingQueue(longs.length);
        BlockingQueue<Long> calcQueue = new ArrayBlockingQueue(longs.length);
        
        for(long lo : longs){
            queue.add(lo);
            System.out.println("Long added: " + lo);
        }
        
        Consumer consumer = new Consumer(calcQueue);
        Producer producer = new Producer(queue, calcQueue, consumer);
        
        long startTime = System.currentTimeMillis();
        
        for(int i = 0; i < producerThreads; i++){
            new Thread(producer).start();
        }
        
        Thread consumerT = new Thread(consumer);
        consumerT.start();
        consumerT.join();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime);
        System.exit(0);
    }

    
}

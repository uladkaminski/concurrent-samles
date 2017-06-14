package com.epam.jmp.syncmbus;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
@SuppressWarnings("Duplicates")
public class ProducerConsumerRunner {
    private final static Logger log = Logger.getLogger(ProducerConsumerRunner.class);

    public static void main(String[] args) {
        long start = System.nanoTime();

        BlockingQueue sharedQueue = new LinkedBlockingQueue();

        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        prodThread.start();
        consThread.start();
//        try {
//            prodThread.join();
//            consThread.join();
//        } catch (InterruptedException e) {
//            log.error(e);
//        }
//        log.info("BlockingQueue way: " + (System.nanoTime() - start));
        // BlockingQueue way: 7351263445
    }
}

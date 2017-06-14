package com.epam.jmp.nonsyncmbus;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
@SuppressWarnings("Duplicates")
public class Producer implements Runnable {

    public static final long NUMBER_OF_MESSAGES = 99999999L;
    private final Queue<Long> sharedQueue;
    private final Logger log = Logger.getLogger(Producer.class);

    Producer(Queue<Long> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(long i = 1; i<= NUMBER_OF_MESSAGES; i++){
            synchronized (sharedQueue) {
                log.info("Produced: " + i);
                sharedQueue.add(i);
                sharedQueue.notify();
            }

        }
    }
}



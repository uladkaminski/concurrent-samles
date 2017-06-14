package com.epam.jmp.syncmbus;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;

import static com.epam.jmp.nonsyncmbus.Producer.NUMBER_OF_MESSAGES;

@SuppressWarnings("Duplicates")
class Producer implements Runnable {

    private final BlockingQueue sharedQueue;
    private final Logger log = Logger.getLogger(Producer.class);

    Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for(long i = 1; i <= NUMBER_OF_MESSAGES; i++){
            try {
                log.info("Produced: " + i);
                sharedQueue.put(i);
            } catch (InterruptedException ex) {
                log.error(ex);
            }
        }
    }
}



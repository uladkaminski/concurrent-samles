package com.epam.jmp.syncmbus;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;

import static com.epam.jmp.nonsyncmbus.Producer.NUMBER_OF_MESSAGES;

@SuppressWarnings("Duplicates")
class Consumer implements Runnable {

    private final BlockingQueue sharedQueue;
    private final Logger log = Logger.getLogger(Consumer.class);

    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            Long msg = null;
            try {
                msg = Long.valueOf(sharedQueue.take().toString());
                log.info("Consumed: " + msg);
            } catch (InterruptedException ex) {
                log.error(ex);
            }
            if (msg.equals(NUMBER_OF_MESSAGES)){
                return;
            }

        }
    }
}



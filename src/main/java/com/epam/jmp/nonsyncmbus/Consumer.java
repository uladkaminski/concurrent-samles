package com.epam.jmp.nonsyncmbus;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static com.epam.jmp.nonsyncmbus.Producer.NUMBER_OF_MESSAGES;

@SuppressWarnings("Duplicates")
class Consumer implements Runnable {

    private final Queue<Long> sharedQueue;
    private final Logger log = Logger.getLogger(Consumer.class);

    Consumer(Queue<Long> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQueue) {
                if (sharedQueue.isEmpty()) {
                    try {
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        log.error(e);
                    }
                } else {
                    Long msg = sharedQueue.poll();
                    log.info(String.format("Consumed %d: %d", Thread.currentThread().getId(), msg));
                    if (msg.equals(NUMBER_OF_MESSAGES)){
                        return;
                    }
                }
            }
        }
    }
}



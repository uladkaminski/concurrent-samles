package com.epam.jmp.nonsyncmbus;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("Duplicates")
public class ProducerConsumerRunner {
    private final static Logger log = Logger.getLogger(ProducerConsumerRunner.class);

    public static void main(String[] args) {

        long start = System.nanoTime();
        Queue<Long> sharedQueue = new LinkedList<>();

        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        prodThread.start();
        consThread.start();
        try {
            prodThread.join();
            consThread.join();
        } catch (InterruptedException e) {
            log.error(e);
        }
        log.info("Wait/notify way: " + (System.nanoTime() - start));
        // n = 999999L Wait/notify way: 6785958652
        //           BlockingQueue way: 7351263445
        // n = 99999999L Wait/notify way: 5081528533527
        //             BlockingQueue way: 6194729567701
    }
}

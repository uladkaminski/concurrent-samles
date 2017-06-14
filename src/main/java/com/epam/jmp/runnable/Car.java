package com.epam.jmp.runnable;

import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;


@SuppressWarnings("Duplicates")
public class Car implements Callable<Car> {
    private static final long MAX_DISTANCE = 10000;
    private static final long DISQUALIFIED_TIME_IN_SECONDS = 5;
    private Logger log = Logger.getLogger(getClass());
    private long friction;
    private long distance;
    private boolean shouldBeDisqualified;
    private String name;
    private static AtomicInteger counter;
    private int place;


    Car(String name, long friction) {
        this.name = name;
        this.friction = friction;
        counter = new AtomicInteger(1);
    }

    Car(String name, long friction, boolean shouldBeDisqualified) {
        this.name = name;
        this.friction = friction;
        this.shouldBeDisqualified = shouldBeDisqualified;
        counter = new AtomicInteger(1);
    }

    String getName() {
        return name;
    }

    boolean isWinner() {
        return place == 1;
    }

    @Override
    public Car call() throws Exception {
        LocalTime start = LocalTime.now();
        try {
            while (distance < MAX_DISTANCE) {
                if (shouldBeDisqualified && start.plusSeconds(DISQUALIFIED_TIME_IN_SECONDS).isBefore(LocalTime.now())){
                    log.info("Car " + name + " is disqualified");
                    return null;
                }
                Thread.sleep(friction);
                distance += 100;
                log.info(name + " " + distance);
            }
            place = counter.getAndIncrement();
        } catch (InterruptedException e) {
            log.error(e);
        }
        return this;
    }
}
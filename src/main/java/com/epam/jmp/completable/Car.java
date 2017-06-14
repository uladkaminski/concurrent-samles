package com.epam.jmp.completable;

import org.apache.log4j.Logger;

import java.util.function.Supplier;

public class Car implements Supplier<String> {
    private static final long MAX_DISTANCE = 10000;
    private static final long DISQUALIFY_TIME = 5000;
    Logger log = Logger.getLogger(getClass());
    private long friction;
    private long distance;
    private String name;
    private boolean shouldBeDisqualified;

    Car(String name, long friction) {
        this.name = name;
        this.friction = friction;
    }

    Car(String name, long friction, boolean shouldBeDisqualified) {
        this.name = name;
        this.friction = friction;
        this.shouldBeDisqualified = shouldBeDisqualified;
    }

    @Override
    public String get() {
        long start = System.nanoTime();
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                log.info(name + " " + distance);
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
        return name;
    }
}
package com.epam.jmp.completable;


import org.apache.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CarRace {
    private static final Logger log = Logger.getLogger(CarRace.class);

    public static void main(String[] args) throws InterruptedException {
        Car ferrari = new Car("Ferrari", 100);
        Car bently = new Car("Bently", 99);
        Car bugatty = new Car("Bugatty", 98, true);
        Car mcLaren = new Car("McLaren", 102);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture
                .anyOf(
                        supplyAsync(ferrari, executorService),
                        supplyAsync(bently, executorService),
                        supplyAsync(bugatty, executorService),
                        supplyAsync(mcLaren, executorService)
                )
                .thenAccept(name -> log.info("THE WINNER IS " + name));
        Thread.sleep(1000000);
    }
}

package com.epam.jmp.runnable;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CarRace {
    private static final Logger log = Logger.getLogger(CarRace.class);
    public static final int NUMBER_OF_CARS = 4;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Car ferrari = new Car("Ferrari", 100);
        Car bently = new Car("Bently", 99);
        Car bugatty = new Car("Bugatty", 98, true);
        Car mcLaren = new Car("McLaren", 102);

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CARS);

        List<Future<Car>> futures = Arrays.asList(
                executorService.submit(ferrari),
                executorService.submit(bently),
                executorService.submit(bugatty),
                executorService.submit(mcLaren)
        );
        List<Car> finishedCars = new ArrayList<>(NUMBER_OF_CARS);

        for (Future<Car> future : futures) {
            finishedCars.add(future.get());
        }

        for (Car car : finishedCars) {
            if (car != null && car.isWinner()) {
                log.info("THE WINNER IS " + car.getName());
            }
        }
    }
}

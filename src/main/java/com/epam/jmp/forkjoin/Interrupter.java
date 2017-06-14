package com.epam.jmp.forkjoin;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;


public class Interrupter implements Runnable{
    List<ForkJoinPool> forkJoinPools;

    public Interrupter(List<ForkJoinPool> forkJoinPools) {
        this.forkJoinPools = forkJoinPools;
    }


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String str = scanner.next();
            if ("exit".equals(str)){
                forkJoinPools.forEach(ForkJoinPool::shutdownNow);
                return;
            }
        }
    }
}

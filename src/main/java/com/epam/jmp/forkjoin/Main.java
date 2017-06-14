package com.epam.jmp.forkjoin;


import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        File file = new File("c:\\jmp\\");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinPool forkJoinPool1 = new ForkJoinPool();

        Interrupter interrupter = new Interrupter(Arrays.asList(forkJoinPool, forkJoinPool1));
        Thread thread = new Thread(interrupter);
        thread.setDaemon(true);
        thread.start();


        FileCounter fileCounter = new FileCounter(file);
        FolderCounter folderCounter = new FolderCounter(file);

        Long fileCount = forkJoinPool.invoke(fileCounter);
        Long folderCount = forkJoinPool1.invoke(folderCounter);

        LOG.info("FILES: " + fileCount);
        LOG.info("DIRECTORIES: " + folderCount);
    }
}

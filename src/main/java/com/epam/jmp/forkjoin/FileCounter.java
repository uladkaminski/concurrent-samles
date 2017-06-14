package com.epam.jmp.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCounter extends RecursiveTask<Long>{
    private File file;

    public FileCounter(File file) {
        this.file = file;
    }

    @Override
    protected Long compute() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long fileCount = numberOfFiles(file.listFiles());
        List<FileCounter> fileCounters = new ArrayList<>();
        List<File> directories = filterDirectories(file.listFiles());
        for (File directory : directories) {
            FileCounter task = new FileCounter(directory);
            task.fork();
            fileCounters.add(task);
        }


        for (FileCounter fileCounter : fileCounters) {
            fileCount += fileCounter.join();
        }
        return fileCount;
    }

    public static List<File> filterDirectories(File[] files) {
        return Stream.of(files)
                .filter(File::isDirectory)
                .collect(Collectors.toList());
    }

    private Long numberOfFiles(File[] files) {
        return Stream.of(files)
                .filter(file -> !file.isDirectory())
                .count();
    }
}

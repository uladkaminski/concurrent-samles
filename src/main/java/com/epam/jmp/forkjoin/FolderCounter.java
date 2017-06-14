package com.epam.jmp.forkjoin;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

import static com.epam.jmp.forkjoin.FileCounter.filterDirectories;

public class FolderCounter extends RecursiveTask<Long> {
    private File file;

    public FolderCounter(File file) {
        this.file = file;
    }
    @Override
    protected Long compute() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long folderCount = numberOfDirectories(file.listFiles());
        List<FolderCounter> folderCounters = new ArrayList<>();
        List<File> directories = filterDirectories(file.listFiles());
        for (File directory : directories) {
            FolderCounter task = new FolderCounter(directory);
            task.fork();
            folderCounters.add(task);
        }

        for (FolderCounter folderCounter : folderCounters) {
            folderCount += folderCounter.join();
        }
        return folderCount;
    }

    private Long numberOfDirectories(File[] files) {
        return Stream.of(files)
                .filter(File::isDirectory)
                .count();
    }
}

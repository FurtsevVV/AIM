package main.java;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class AimTask {
    public static void main(String[] args) {

       File file1 = new File("C:/Users/Zakat/IdeaProjects/AIMTask/src/main/resources/input1.csv");

        File file2 = new File("C:/Users/Zakat/IdeaProjects/AIMTask/src/main/resources/input2.csv");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new FileHandler(file1));
        executorService.submit(new FileHandler(file2));
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


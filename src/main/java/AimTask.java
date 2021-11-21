package main.java;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class AimTask {
    public static void main(String[] args) {

        List<File> files = new ArrayList<>();
        while (true) {
            System.out.println("Enter path to file (example C:/Users/input1.csv) or Done to finish input:");
            Scanner scanner = new Scanner(System.in);

            String path = scanner.nextLine();
            if (path.equals("Done"))
                break;

            File file1 = new File(path);
            files.add(file1);

        }
        //  File file1 = new File("C:/Users/Zakat/IdeaProjects/AIMTask/src/main/resources/input1.csv");

        //    File file2 = new File("C:/Users/Zakat/IdeaProjects/AIMTask/src/main/resources/input2.csv");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < files.size(); i++) {
            executorService.submit(new FileHandler(files.get(i)));
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler implements Runnable {
    private File file;

    public FileHandler(File file) {
        this.file = file;
    }

    @Override
    public void run() {

        scanFile(file);
    }

    public static boolean isNewValue(File file, String stringReadyToWrite) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] strArrayForCheckWriteValue = scanner.nextLine().split(";");
                for (int i = 0; i < strArrayForCheckWriteValue.length; i++) {
                    if (strArrayForCheckWriteValue[i].equals(stringReadyToWrite))
                        return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void scanFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String str = scanner.nextLine();
            String[] arrayWithNameOfFiles = str.split(";");
            for (int i = 0; i < arrayWithNameOfFiles.length; i++) {
                Scanner scanner1 = new Scanner(file);
                scanner1.nextLine();
                String filepath = "C:/Users/Zakat/IdeaProjects/AIMTask/src/resources/" + arrayWithNameOfFiles[i] + ".txt";
                String filename = arrayWithNameOfFiles[i] + ".txt";
                File chekedFile = new File(filepath);
                if (chekedFile.createNewFile())
                    System.out.println(filename + " is created");
                FileWriter writer = new FileWriter(filepath, true);
                while (scanner1.hasNextLine()) {
                    String[] arrayWithValues = scanner1.nextLine().split(";");
                    String writedValue = arrayWithValues[i];
                    if (isNewValue(chekedFile, writedValue)) {
                        writer.write(writedValue + ";");
                        writer.flush();
                    }
                }
                scanner1.close();

            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

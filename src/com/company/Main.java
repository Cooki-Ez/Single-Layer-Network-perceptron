package com.company;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<File> arrayTestList = Stream.of(new File("test").listFiles()).collect(Collectors.toList());
    static List<File> arrayTrainingList = Stream.of(new File("training").listFiles()).collect(Collectors.toList());
    private static Reader readerForTraining;
    public static void main(String[] args) {
        readerForTraining = new Reader(arrayTrainingList);
        begin();
    }

    private static void begin(){
        System.out.println("Enter 1 to start test");
        System.out.println("Enter 2 to input manually");
        System.out.println("Enter anything to exit");
        Scanner scanner = new Scanner(System.in);
        int fromUser = scanner.nextInt();
        switch (fromUser){
            case 1 -> Reader.beginTest(arrayTestList);
            case 2 -> new UserInterface();
            default -> System.exit(0);
        }
        Main.begin();
    }
}

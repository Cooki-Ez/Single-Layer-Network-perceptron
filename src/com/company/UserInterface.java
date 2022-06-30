package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserInterface {
    private static final String pathToFile = "fromUser.txt";

    public UserInterface() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input text -> ");
            String line = scanner.nextLine();
            File file = new File(pathToFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
            Reader.test(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

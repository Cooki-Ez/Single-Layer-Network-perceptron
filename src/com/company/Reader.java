package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {
    static List<Perceptron> trainingList = new ArrayList<>();

    public Reader(List<File> pathToTraining) {
        System.out.println("Training");
        trainingList = readingFromTrainingDirectory(pathToTraining);
    }

    public static void beginTest(List<File> pathToTest) {
        System.out.println("Testing");
        test(pathToTest);
    }

    private static List<Perceptron> readingFromTrainingDirectory(List<File> pathToFiles) {
        List<Perceptron> list = new ArrayList<>();
        for (var file : pathToFiles) {
            File[] files = file.listFiles();
            Perceptron perceptron = new Perceptron(file.getName());
            Map<Character, Integer> characterAppearance = null;
            for (File value : files)
                characterAppearance = readingFromFile(value);
            perceptron.normalize(characterAppearance);
            list.add(perceptron);
        }
        return list;
    }

    private static List<Perceptron> readingFromTestDirectory(List<File> pathToFiles) {
        List<Perceptron> list = new ArrayList<>();
        for (var file : pathToFiles) {
            File[] files = file.listFiles();
            for (File value : files) {
                Perceptron perceptron = new Perceptron(file.getName());
                perceptron.normalize(readingFromFile(value));
                list.add(perceptron);
            }
        }
        return list;
    }

    private static Perceptron oneFile(File file) {
        Perceptron perceptron = new Perceptron(file.getName());
        perceptron.normalize(readingFromFile(file));
        return perceptron;
    }

    private static void test(List<File> pathToFiles) {
        List<Perceptron> toTest = readingFromTestDirectory(pathToFiles);
        for (var fromTestPerceptron : toTest) {
            Map<Perceptron, Double> activationCount = new HashMap<>();
            for (var fromTrainingPerceptron : trainingList) {
                activationCount.put(fromTrainingPerceptron, fromTrainingPerceptron.test(fromTestPerceptron));
            }
            double maxKey = 0;
            Perceptron result = null;
            for (var key : activationCount.keySet()) {
                if (activationCount.get(key) > maxKey) {
                    maxKey = activationCount.get(key);
                    result = key;
                }
            }
            System.out.println("Result: " + result.getLanguage() + ". Real: " + fromTestPerceptron.getLanguage());
        }
    }

    public static void test(File file) {
        Perceptron perceptron = oneFile(file);
        Map<Perceptron, Double> activationCount = new HashMap<>();
        for (var fromTrainingPerceptron : trainingList)
            activationCount.put(fromTrainingPerceptron, fromTrainingPerceptron.test(perceptron));
        double max = 0.0;
        Perceptron answer = null;
        for (var key: activationCount.keySet()){
            if(activationCount.get(key) > max) {
                max = activationCount.get(key);
                answer = key;
            }
        }

        System.out.println("Result: " + answer.getLanguage());
    }


    private static Map<Character, Integer> readingFromFile(File file) {
        Map<Character, Integer> characterAppearance = new HashMap<>();
        try (FileReader fl = new FileReader(file)) {
            int content;
            while ((content = fl.read()) != -1)
                if (checkIfLatin((char) content))
                    characterAppearance.merge(Character.toLowerCase((char) content), 1, Integer::sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterAppearance;
    }

    private static boolean checkIfLatin(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

}

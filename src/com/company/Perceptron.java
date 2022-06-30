package com.company;

import java.util.*;

public class Perceptron {
    private static final double LEARNING_RATE = 0.01;
    private final List<Double> normalizedVectors = new ArrayList<>();
    private final List<Double> weights = new ArrayList<>();
    private double threshold = 1;
    private final String language;

    public Perceptron(String language) {
        this.language = language;
    }

    public void normalize(Map<Character, Integer> characterAppearance) {
        List<Integer> vectors = new ArrayList<>(characterAppearance.values());
        double length = 0;
        for (var vector : vectors)
            length += Math.pow(vector, 2);
        length = Math.sqrt(length);
        for (var vector : vectors) {
            normalizedVectors.add(vector / length);
            weights.add(0.0);
        }
        setting();
    }

    private void setting() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < weights.size(); j++) {
                double output = 1 - algorithm();
                for (int k = 0; k < normalizedVectors.size(); k++) {
                    double newWeight = weights.get(k) + LEARNING_RATE * output * normalizedVectors.get(k);
                    weights.set(k, newWeight);
                }
                threshold -= LEARNING_RATE * output;
            }
        }
    }

    private int algorithm() {
        double net = 0;
        for (int i = 0; i < weights.size(); i++) {
            net += weights.get(i) * normalizedVectors.get(i);
        }
        return net >= threshold ? 1 : 0;
    }

    public double test(Perceptron toTest) {
        double output = 0;
        for (int i = 0; i < toTest.weights.size(); i++) {
            output += toTest.weights.get(i) * weights.get(i);
        }
        output -= threshold;
        return 1 / (1 + Math.exp(-output));
    }

    public boolean algorithm(Perceptron perceptron) {
        double net = 0;
        for (int i = 0; i < perceptron.normalizedVectors.size(); i++) {
            net += weights.get(i) * perceptron.normalizedVectors.get(i);
        }
        System.out.println(net);
        return net >= threshold;
    }

    public String getLanguage() {
        return language;
    }

    public List<Double> getNormalizedVectors() {
        return normalizedVectors;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public double getThreshold() {
        return threshold;
    }
}

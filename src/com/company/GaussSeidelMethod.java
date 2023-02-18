package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GaussSeidelMethod {
    public void printMatrix(double[][] matrix) {
        int size = matrix.length;
        for (double[] doubles : matrix) {
            for (int j = 0; j < size + 1; j++) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public double[][] transformation(double[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            double del = matrix[i][i];
            for (int j = 0; j < size + 1; j++) {
                matrix[i][j] = matrix[i][j] / del;
            }
        }
        return matrix;
    }


    public void methodGaussSeidel(double[][] matrix) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите точность: ");
        double epsilon = scanner.nextDouble();

        int size = matrix.length;
        ArrayList<Double> staticD = new ArrayList<>();
        ArrayList<Double> d = new ArrayList<>();
        ArrayList<Double> xList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            d.add(matrix[i][size]);
            staticD.add(matrix[i][size]);
        }
        int countOfIterations = 0;
        while (true) {
            countOfIterations ++;
            for (int i = 0; i < size; i++) {
                double x = 0;
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        double x1;
                        try {
                            x1 = xList.get(j);
                            x += matrix[i][j] * x1;
//                        System.out.println("x1: " + x1);
                        } catch (IndexOutOfBoundsException e) {
                            x += matrix[i][j] * d.get(j);
                        }
                    }
                }
                x += staticD.get(i);
                if (Double.isInfinite(x)){
                    System.out.println("Infinity " + countOfIterations);
                    break;
                }
                System.out.println("x: " + x);
                xList.add(x);

            }
            double max = Math.abs(xList.get(0) - d.get(0));
            for (int k = 1; k < xList.size(); k++) {
                if ((Math.abs(xList.get(k) - d.get(k))) > max) {
                    max = Math.abs(xList.get(k) - d.get(k));
                    System.out.println("var: " + Math.abs(xList.get(k) - d.get(k)));
                }
            }
            System.out.println("max: " + max);
            if (max < epsilon) {
                break;
            } else {
                d.clear();
                d = (ArrayList<Double>) xList.clone();
                xList.clear();
            }
        }
        System.out.println("Количесво итераций: " + countOfIterations);
    }
}

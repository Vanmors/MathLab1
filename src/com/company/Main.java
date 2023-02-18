package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int size = 0;
        ArrayList<Double> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
        int num = scanner.nextInt();
        while (!(num == 1 || num == 2)) {
            System.out.println("Ошибка ввода!");
            System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
            num = scanner.nextInt();
        }

        if (num == 1) {
            try {
                FileInputStream path = new FileInputStream("res/input");
                DataInputStream inFile = new DataInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
                String data;

                while ((data = br.readLine()) != null) {
                    String[] tmp = data.split(" ");    //Split space
                    for (String s : tmp)
                        arrayList.add(Double.parseDouble(s));
                    size++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через точку");
                System.exit(0);
            }
            System.out.println("Размерность матрицы: ");
            System.out.println(size);
            System.out.println();
        }


        if (num == 2) {
            System.out.println("Укажите размерность матрицы: ");
            size = scanner.nextInt();

            if (size == 1)
                System.out.println("Размерность СЛАУ не может быть равна одному");
            else if (size == 2) {
                System.out.println("Формат ввода: 'a11 a12 b1'");
                System.out.println("Введите коффициенты через пробел:");
            } else {
                System.out.println("Формат ввода: 'a11 ... a1" + size + " b1'");
                System.out.println("Введите коффициенты через пробел:");
            }
            try {
                for (int i = 0; i < size * size + size; i++)
                    arrayList.add(scanner.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через запятую");
            }
        }


        double[][] matrix = new double[size][size + 1];
        int index = 0;
        for (
                int i = 0;
                i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                matrix[i][j] = arrayList.get(index);
                index++;
            }
        }

        GaussSeidelMethod gaussSeidelMethod = new GaussSeidelMethod();
//        gaussSeidelMethod.printMatrix(matrix);

        double[][] transformMatrix = gaussSeidelMethod.transformation(matrix);
        gaussSeidelMethod.printMatrix(transformMatrix);
        System.out.println();
        gaussSeidelMethod.convergence(transformMatrix);
        gaussSeidelMethod.methodGaussSeidel(transformMatrix);
    }
}

package org.example;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введiть n ");
        int n = scanner.nextInt();

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        FizzBuzz fizzBuzz = new FizzBuzz(n, queue);

        Thread fizzThread = new Thread(() -> fizzBuzz.fizz());
        Thread outputThread = new Thread(() -> fizzBuzz.outputResults());
        Thread number = new Thread(() -> fizzBuzz.number());
        Thread buzz = new Thread(() -> fizzBuzz.buzz());
        Thread fizzbuzz = new Thread(() -> fizzBuzz.fizzBuzz());

        fizzThread.start();
        outputThread.start();
        number.start();
        buzz.start();
        fizzbuzz.start();

        while (!queue.isEmpty()) {
            String element = queue.poll();
            System.out.println(element);
        }

        try {
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
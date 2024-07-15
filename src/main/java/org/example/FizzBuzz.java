package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private int n;
    private int currentNumber = 1;
//    private final Object lock = new Object();
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void fizz() {
        while (currentNumber <= n) {
            synchronized (this) {
                if (currentNumber > n) {
                    break;
                }
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    try {
                        queue.put("fizz");
                        currentNumber++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void buzz() {
        while (currentNumber <= n) {
            synchronized (this) {
                if (currentNumber > n) {
                    break;
                }
                if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                    try {
                        queue.put("buzz");
                        currentNumber++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void fizzbuzz() {
        while (currentNumber <= n) {
            synchronized (this) {
                if (currentNumber > n) {
                    break;
                }
                if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                    try {
                        queue.put("fizzbuzz");
                        currentNumber++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void number() {
        while (currentNumber <= n || !queue.isEmpty()) {
            synchronized (this) {
                if (!queue.isEmpty()) {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (currentNumber <= n) {
                    if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                        System.out.println(currentNumber);
                        currentNumber++;
                    }
                }
            }
        }
    }

    public void start() {
        Thread threadA = new Thread(this::fizz);
        Thread threadB = new Thread(this::buzz);
        Thread threadC = new Thread(this::fizzbuzz);
        Thread threadD = new Thread(this::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
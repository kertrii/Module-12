package org.example;

import java.util.Queue;

public class FizzBuzz {
    private int n;
    private Queue queue;
    private int currenNumber = 1;

    public FizzBuzz(int n, Queue queue) {
        this.n = n;
        this.queue = queue;
    }

    public void fizz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 == 0 && currenNumber % 5 != 0) {
                    queue.add("Fizz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void number () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 != 0 && currenNumber % 5 != 0) {
                    queue.add(currenNumber);
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void buzz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 != 0 && currenNumber % 5 == 0) {
                    queue.add("Buzz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void fizzBuzz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 == 0 && currenNumber % 5 == 0) {
                    queue.add("FizzBuzz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void outputResults() {
        while (currenNumber <= n) {
            while (!queue.isEmpty()) {
                Object element = queue.poll();
                System.out.println(element);
            }

        }
    }
}
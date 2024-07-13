package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {

    private final int n;
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final AtomicInteger current = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        while (true) {
            int num = current.get();
            if (num > n) return;
            if (num % 3 == 0 && num % 5 != 0) {
                queue.put("fizz");
                current.incrementAndGet();
            }
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            int num = current.get();
            if (num > n) return;
            if (num % 5 == 0 && num % 3 != 0) {
                queue.put("buzz");
                current.incrementAndGet();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            int num = current.get();
            if (num > n) return;
            if (num % 3 == 0 && num % 5 == 0) {
                queue.put("fizzbuzz");
                current.incrementAndGet();
            }
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            int num = current.get();
            if (num > n) return;
            if (num % 3 != 0 && num % 5 != 0) {
                queue.put(Integer.toString(num));
                current.incrementAndGet();
            }
        }
    }

    public void print() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            System.out.print(queue.take() + (i < n ? ", " : "\n"));
        }
    }
}

package org.example;

public class TimerApp {
    public void start() {
        Thread timeDisplayThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Час, що минув: " + elapsedTime / 1000 + " секунд");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeDisplayThread.start();

        Thread fiveSecondsMessageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fiveSecondsMessageThread.start();
    }
}

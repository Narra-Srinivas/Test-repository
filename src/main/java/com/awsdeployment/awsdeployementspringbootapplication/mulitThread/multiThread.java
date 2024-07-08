package com.awsdeployment.awsdeployementspringbootapplication.mulitThread;

public class multiThread {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 is running");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 is running");
        });

        thread1.start();
        thread2.start();
    }
}

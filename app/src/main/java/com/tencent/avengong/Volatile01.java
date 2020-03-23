package com.tencent.avengong;

public class Volatile01 {

    public static void main(String[] args) {

        IncreaseRunnable increaseRunnable = new IncreaseRunnable();

        for (int i = 0; i < 100; i++) {

            new Thread(increaseRunnable, "第 " + i + "个线程").start();

        }

    }


    static class IncreaseRunnable implements Runnable {

        private volatile int count;

        @Override
        public void run() {
            synchronized (IncreaseRunnable.class) {

                for (int i = 0; i < 10000; i++) {

                    count++;
                    System.out.println(Thread.currentThread().getName() + " count :" + count);
                }
            }
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {


        }
    }

}

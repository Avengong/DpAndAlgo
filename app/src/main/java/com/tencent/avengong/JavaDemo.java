package com.tencent.avengong;

public class JavaDemo {

    static String TAG = "JavaDemo";
    private static TestPrinter testPrinter;

    public static void main(String[] args) {
//        TestThread testThread = new TestThread();

//        testThread.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        testThread.interrupt();


        testPrinter = new TestPrinter();
        SynchronizeThread1 synchronizeThread1 = new SynchronizeThread1();
        SynchronizeThread2 synchronizeThread2 = new SynchronizeThread2();
        synchronizeThread1.start();
        synchronizeThread2.start();

    }


    static class TestThread extends Thread {

        long i;


        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                if (i < 100000) {
                    i++;
                    System.out.println(TAG + "i: " + i);
                }
            }
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            System.out.println(TAG + "sopped!!  i= " + i);
            for (StackTraceElement element : stackTrace) {

                System.out.println(TAG + "stack !!  = " + element);
            }
        }
    }


    static class SynchronizeThread1 extends Thread {


        @Override
        public void run() {

            testPrinter.print3();
//            testPrinter.print2();
        }
    }

    static class SynchronizeThread2 extends Thread {


        @Override
        public void run() {
//            testPrinter.print2();
//            testPrinter.print();
            TestPrinter.print2();
        }
    }


}

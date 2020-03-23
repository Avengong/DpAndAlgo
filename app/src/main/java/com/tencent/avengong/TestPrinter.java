package com.tencent.avengong;

import java.util.concurrent.TimeUnit;

public class TestPrinter {

    Object mLock = new Object();

    public static synchronized void print2() {
        System.out.println(" wo shi printer22。 threadname：" + Thread.currentThread().getName() + ", object: ");
    }

    public void print() {

//        wo shi printer。 threadname：Thread-0object: 184719164
//        wo shi printer。 threadname：Thread-1object: 1956074645

        synchronized (mLock) {

            System.out.println(" wo shi printer。 threadname：" + Thread.currentThread().getName() + ", object: " + this.toString());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void print3() {

//        wo shi printer。 threadname：Thread-0object: 184719164
//        wo shi printer。 threadname：Thread-1object: 1956074645


        System.out.println(" wo shi printer。 threadname：" + Thread.currentThread().getName() + ", object: " + this.toString());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

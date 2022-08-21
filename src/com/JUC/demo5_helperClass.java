package com.JUC;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class demo5_helperClass {
}

//CountDownLatch
class CDL{
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 4; i++) {
            int tmp = i;
            new Thread(()->{
                System.out.println("it's my thread" + tmp);
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        new Thread(()->
        {
            System.out.println("new Thread starting ...");
        }).start();
    }
}

class CB {
    public static void main(String[] args) {
        /**
         * 集齐77个龙珠召唤神龙
         */
        // 召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙成功! ");
        });
        for (int i = 0; i < 7; i++) {
            int temp = i;
            //lambda 能拿到i吗
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");


                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static Runnable printMe() {
        System.out.println("wanchengla");
        return null;
    }
}


class Semp {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 500; i++) {
            int tmp = i;
            new Thread(()->{
                System.out.println("thread" + tmp);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}

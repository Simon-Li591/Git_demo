package com.JUC;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class demo2_RWLock {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(counter);
        }
    }
}


class Counter implements Runnable {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private int[] counts = new int[10];


    public void inc(int index) {
        writeLock.lock();
        try {
            counts[index] += 1;
        } finally {
            writeLock.unlock();
        }
    }

    public int[] get() {
        readLock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void run() {
        inc(1);
        int[] res = get();
        for (int resa:
             res) {
            System.out.println(resa);
        }
    }
}
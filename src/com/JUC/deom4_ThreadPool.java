package com.JUC;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class deom4_ThreadPool{
}


class method3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorSerivice = Executors.newFixedThreadPool(5);
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        ExecutorService threadPool =
                new ThreadPoolExecutor(3,
                        5,
                        5,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.DiscardOldestPolicy()
                );
    }
}

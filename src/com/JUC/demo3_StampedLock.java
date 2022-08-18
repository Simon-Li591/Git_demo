package com.JUC;

import java.util.concurrent.locks.StampedLock;

/*
和ReadWriteLock相比，写入的加锁是完全一样的，不同的是读取。注意到首先我们通过tryOptimisticRead()获取
一个乐观读锁，并返回版本号。接着进行读取，读取完成后，我们通过validate()去验证版本号，
如果在读取过程中没有写入，版本号不变，验证成功，我们就可以放心地继续后续操作。如果在读取过程中有写入，
版本号会发生变化，验证将失败。在失败的时候，我们再通过获取悲观读锁再次读取。由于写入的概率不高，
程序在绝大部分情况下可以通过乐观读锁获取数据，极少数情况下使用悲观读锁获取数据。

可见，StampedLock把读锁细分为乐观读和悲观读，能进一步提升并发效率。但这也是有代价的：
一是代码更加复杂，二是StampedLock是不可重入锁，不能在一个线程中反复获取同一个锁。

StampedLock还提供了更复杂的将悲观读锁升级为写锁的功能，它主要使用在if-then-update的场景：
即先读，如果读的数据满足条件，就返回，如果读的数据不满足条件，再尝试写。
 */
public class demo3_StampedLock {
    //    private final StampedLock stampedLock = new StampedLock();
//    private double x;
//    private double y;
//
//    public void move(double deltaX, double deltaY) {
//        long stamp = stampedLock.writeLock();
//        try {
//            x += deltaX;
//            y += deltaY;
//        } finally {
//            stampedLock.unlockWrite(stamp);
//        }
//    }
//
//    public double distanceFromOrigin() {
//        long stamp = stampedLock.tryOptimisticRead();
//        // 注意下面两行代码不是原子操作
//        // 假设x,y = (100,200)
//        double currentX = x;
//        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
//        double currentY = y;
//        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
//        // 如果有写入，读取是错误的(100,400)
//        if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
//            stamp = stampedLock.readLock();
//            try {
//                currentX = x;
//                currentY = y;
//            }finally {
//                stampedLock.unlockWrite(stamp);
//            }
//        }
//        return Math.sqrt(currentX * currentX + currentY * currentY);
//    }
    private final StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double detaX, double daltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x = detaX;
            y = daltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if (!stampedLock.validate(stamp)) {
            try {
                stamp = stampedLock.readLock();
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
    public void get() {
        System.out.println("x = " + x + "    y = " + y);
    }

}

class new1 {
    public static void main(String[]args){
        demo3_StampedLock demo3_stampedLock = new demo3_StampedLock();
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            new Thread(()-> {
                demo3_stampedLock.move(tmp + 2,tmp+1);
            }).start();
            System.out.println(tmp + "  move " );
            demo3_stampedLock.get();
        }
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            new Thread(demo3_stampedLock::distanceFromOrigin).start();
            System.out.println(tmp + "  distanceFromOrigin ");
            demo3_stampedLock.get();

        }
    }
}
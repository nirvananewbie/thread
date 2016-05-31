package com.xusen.condition.LockAndCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class LockAndConditionDemo {

    public static int value = 1;
    //初始化可重入锁
    public static final Lock lock = new ReentrantLock();

    //第一个条件当屏幕上输出到3
    public static final Condition reachThreeCondition = lock.newCondition();
    //第二个条件当屏幕上输出到6
    public static final Condition reachSixCondition = lock.newCondition();

    public static void main(String[] args){
        /*//初始化A线程
        Thread threadA = new Thread(new ThreadA());
        //初始化线程B
        Thread threadB = new Thread(new ThreadB());

        //启动两个线程
        threadB.start();
        threadA.start();*/
        //使用线程池技术
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new ThreadA());
        threadPool.execute(new ThreadB());
        threadPool.shutdown();
    }
}

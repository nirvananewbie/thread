package com.xusen.thread;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class Army implements Runnable {

    volatile boolean keepRunning = true;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始进攻了！");
        while(keepRunning){
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"进攻了["+i+"]次");
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread().getName()+"结束了战斗！");
    }
}

package com.xusen.condition.LockAndCondition;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class ThreadA implements Runnable {
    @Override
    public void run() {
        //需要先获得锁
        LockAndConditionDemo.lock.lock();
        try{
            System.out.println("threadA start write");
            //A线程先输出前3个数
            while(LockAndConditionDemo.value <= 3){
                System.out.println(LockAndConditionDemo.value++);
            }
            //输出到3时要signal，告诉B线程可以开始了
            LockAndConditionDemo.reachThreeCondition.signal();
        }finally {
            LockAndConditionDemo.lock.unlock();
        }
        LockAndConditionDemo.lock.lock();
        try {
            //等待输出6的条件
            LockAndConditionDemo.reachSixCondition.await();
            System.out.println("threadA start write");
            //输出剩余数字
            while (LockAndConditionDemo.value <= 9) {
                System.out.println(LockAndConditionDemo.value++);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LockAndConditionDemo.lock.unlock();
        }
    }
}

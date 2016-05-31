package com.xusen.condition.LockAndCondition;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class ThreadB implements Runnable {
    @Override
    public void run() {
        LockAndConditionDemo.lock.lock();
        try{
            while(LockAndConditionDemo.value<=3){
                LockAndConditionDemo.reachThreeCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LockAndConditionDemo.lock.unlock();
        }
        LockAndConditionDemo.lock.lock();
        try{
            //已经收到信号，开始输出4，5，6
            System.out.println("threadB start write");
            while(LockAndConditionDemo.value<=6){
                System.out.println(LockAndConditionDemo.value++);
            }
            //4，5，6输出完毕，告诉A线程6输出完了
            LockAndConditionDemo.reachSixCondition.signal();
        }finally {
            LockAndConditionDemo.lock.unlock();
        }
    }
}

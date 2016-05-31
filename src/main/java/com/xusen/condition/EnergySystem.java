package com.xusen.condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class EnergySystem {
    private final double[] energyBoxes;

    private final Object lockObj = new Object();

    private final Lock lock = new ReentrantLock();

    public EnergySystem(int n,double initialEnergy){
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++)
            energyBoxes[i] = initialEnergy;
    }

    /**
     * 能量的转移，从一个盒子到另一个盒子
     * @param from 能量源
     * @param to     能量终点
     * @param amount 能量值
     */
    public void transfer(int from, int to, double amount){
        /*synchronized (lockObj){
            while (energyBoxes[from] < amount){
                try {
                    //条件不满足, 将当前线程放入Wait Set
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            System.out.printf("从%d转移%10.2f单位能量到%d",from,amount,to);
            energyBoxes[to] += amount;
            System.out.printf("能量总和：%10.2f%n",getTotalEnergies());

            lockObj.notifyAll();
        }*/
        if(lock.tryLock()){
            try {
                System.out.println(Thread.currentThread().getName());
                energyBoxes[from] -= amount;
                System.out.printf("从%d转移%10.2f单位能量到%d",from,amount,to);
                energyBoxes[to] += amount;
                System.out.printf("能量总和：%10.2f%n",getTotalEnergies());
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }

    /**
     * 获取能量世界的能量总和
     */
    public double getTotalEnergies(){
        double sum = 0;
        for (double amount : energyBoxes)
            sum += amount;
        return sum;
    }

    /**
     * 返回能量盒子的长度
     */
    public  int getBoxAmount(){
        return energyBoxes.length;
    }
}

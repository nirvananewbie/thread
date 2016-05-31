package com.xusen.thread;

/**
 * Created by xusen@asiainfo.com on 2016/5/31.
 */
public class Stage extends Thread {
    public void run() {
        System.out.println("欢迎观看隋唐演义");
        //让观众们安静片刻，等待大戏上演
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("大幕徐徐拉开");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("话说隋朝末年，隋军与农民起义军杀得昏天黑地...");

        Army suijun = new Army();
        Army nongmin = new Army();
        Thread sj = new Thread(suijun,"隋军");
        Thread nm = new Thread(nongmin,"农民");
        sj.start();
        nm.start();

        //舞台线程休眠，大家专心观看军队厮杀
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正当双方激战正酣，半路杀出了个程咬金");

        Thread  mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");

        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");

        suijun.keepRunning = false;
        nongmin.keepRunning = false;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		/*
		 * 历史大戏留给关键人物
		 */
        mrCheng.start();

        //万众瞩目，所有线程等待程先生完成历史使命
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("战争结束，人民安居乐业，程先生实现了积极的人生梦想，为人民作出了贡献！");
        System.out.println("谢谢观看隋唐演义，再见！");
    }
}

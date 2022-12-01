package org.hgd.interview.thread;

import org.hgd.interview.utils.LoggerUtils;

import static org.hgd.interview.utils.LoggerUtils.*;

/**
 * @description:
 * @author：hgd
 * @date: 2022/11/23
 */
public class SleepVsWait {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 等待过程中其他线程可以获取该锁
//        waiting();
        // sleep 等待过程中不会释放锁
        sleeping();
    }

    private static void illegalWait() throws InterruptedException {
        LOCK.wait();
    }

    private static void waiting() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    LoggerUtils.get("t").debug("waiting...");
                    LOCK.wait(5000L);
                } catch (InterruptedException e) {
                    LoggerUtils.get("t").debug("interrupted...");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(100);
        synchronized (LOCK) {
            main.debug("other...");
        }
    }

    private static void sleeping() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    get("t").debug("sleeping...");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    get("t").debug("interrupted...");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
        // 中断等待
        t1.interrupt();
//        Thread.sleep(100);
//        synchronized (LOCK) {
//            main.debug("other...");
//        }
    }

}

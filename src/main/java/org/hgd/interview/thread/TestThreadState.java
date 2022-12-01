package org.hgd.interview.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hgd.interview.utils.LoggerUtils.logger1;
import static org.hgd.interview.utils.LoggerUtils.main;


/**
 * @description:
 * @author：hgd
 * @date: 2022/11/21
 */
public class TestThreadState {
    private static final Object LOCK = new Object();
    private final static Logger LOGGER = LoggerFactory.getLogger(TestThreadState.class);

    public static void main(String[] args) {
//        testNewRunnableTerminated();
//        testBlocked();
        testWaiting();
    }

    private static void testWaiting(){
        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                logger1.debug("before waiting");
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
        t2.start();
        main.debug("state:{}",t2.getState());
        synchronized (LOCK){
            main.debug("state:{}",t2.getState());
            LOCK.notify();
            main.debug("state:{}",t2.getState());
        }
        main.debug("state:{}",t2.getState());
    }

    private static void testBlocked() {
        Thread t2 = new Thread(() -> {
            // 3
            logger1.debug("before sync");
            synchronized (LOCK) {
                // 4
                logger1.debug("in sync");
            }
        }, "T2");
        t2.start();
        // 1
        main.debug("state: {}", t2.getState());
        synchronized (LOCK) {
            // 2
            main.debug("state: {}", t2.getState());
        }
        // 5
        main.debug("state: {}", t2.getState());
    }

    private static void testNewRunnableTerminated() {
        Thread t1 = new Thread(() -> {
            //3.Running 状态
            logger1.debug("Running...");
        }, "T1");
        //2. New 状态
        main.debug("state: {}", t1.getState());
        t1.start();
        //2. Runnable 状态
        main.debug("state:{}",t1.getState());
        //4.Terminated状态
        main.debug("state:{}", t1.getState());
    }
}

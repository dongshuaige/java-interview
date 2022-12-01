package org.hgd.interview.thread;

import org.hgd.interview.utils.LoggerUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：hgd
 * @date: 2022/11/25
 */
public class TestOomThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        LoggerUtils.get().debug("begin...");
        while (true) {
            executor.submit(() -> {
                try {
                    LoggerUtils.get().debug("send sms...");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

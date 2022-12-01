package org.hgd.interview.pattern;

import java.io.Serializable;

/**
 * @description: 懒汉式
 * @author：hgd
 * @date: 2022/11/20
 */
public class Singleton3 extends BaseSingleton implements Serializable {
    private Singleton3() {
        System.out.println("private Singleton3()");
    }

    private static Singleton3 INSTANCE = null;

    /**
     * synchronized 给当前类加锁,保证多线程下getInstance()工作时的正确性
     * 其实只有首次创建单例对象才需要同步，但该代码实际上每次调用都会同步，这样就导致性能较为低
     */
    public static synchronized Singleton3 getInstance() {
        if (INSTANCE == null) {
            // 实例化
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }

}

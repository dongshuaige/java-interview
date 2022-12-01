package org.hgd.interview.pattern;

import java.io.Serializable;

/**
 * @description: 懒汉式-内部类
 * @author：hgd
 * @date: 2022/11/20
 */
public class Singleton5 extends BaseSingleton implements Serializable {
    private Singleton5() {
        System.out.println("private Singleton5()");
    }

    public static class Holder {
        static Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Holder.INSTANCE;
    }
}

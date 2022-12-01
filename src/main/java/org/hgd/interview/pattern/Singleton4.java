package org.hgd.interview.pattern;

import java.io.Serializable;

/**
 * @description: 懒汉式单例-DCL 双重锁检查
 * @author：hgd
 * @date: 2022/11/20
 */
public class Singleton4 extends BaseSingleton implements Serializable {
    private Singleton4(){
        System.out.println("private Singleton4()");
    }

    /**
     * volatile : 解决了共享变量的 可见性和有序性
     */
    private static volatile Singleton4 INSTANCE = null;

    public static Singleton4 getInstance(){
        if (INSTANCE==null) {
            // 解决多线程下的锁竞争问题
            synchronized (Singleton4.class) {
                if (INSTANCE==null){
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }


}

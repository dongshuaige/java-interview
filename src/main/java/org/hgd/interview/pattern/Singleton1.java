package org.hgd.interview.pattern;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 饿汉式:初始化类就提前创建实例
 * @author：hgd
 * @date: 2022/11/20
 */
public class Singleton1 extends BaseSingleton implements Serializable {
    /**
     * 私有构造
     */
    private Singleton1() {
        // 预防反射破坏单例
        if (INSTANCE != null) {
            throw new RuntimeException("单例对象不能重复创建!!!");
        }
        System.out.println("private Singleton1()");
    }

    /**
     * 静态成员变量
     */
    public final static Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    /**
     * 预防反序列化破坏单例
     */
   @Serial
   public Object readResolve(){
        return INSTANCE;
   }
}

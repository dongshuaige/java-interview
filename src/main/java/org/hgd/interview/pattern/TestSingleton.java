package org.hgd.interview.pattern;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author：hgd
 * @date: 2022/11/20
 */
public class TestSingleton {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        Singleton2.otherMethod();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton2.getInstance());
        //如果实现了Serializable,可能会通过反射破单例
        // 反射破坏单例
//        reflection(Singleton1.class);
        reflection1(Singleton2.class);

        // 反序列化破坏单例
//        serializable(Singleton1.getInstance());

        // Unsafe 破坏单例--> 无法预防
//        unsafe(Singleton1.class);

    }

    private static void unsafe(Class<?> clazz) throws InstantiationException {
        Object o = UnsafeUtils.getUnsafe().allocateInstance(clazz);
        System.out.println("Unsafe 创建实例:" + o);
    }

    private static void serializable(Object instance) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(instance);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        System.out.println("反序列化创建实例:" + ois.readObject());
    }

    private static void reflection(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println("反射创建实例:" + constructor.newInstance());
    }

    private static void reflection1(Class<?> clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        System.out.println("反射创建实例:" + constructor.newInstance("OTHER",1));
    }
}

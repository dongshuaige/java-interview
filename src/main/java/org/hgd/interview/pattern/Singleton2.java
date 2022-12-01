package org.hgd.interview.pattern;

/**
 * 枚举饿汉式
 * @author hgd
 */
public enum Singleton2 {
    INSTANCE;

    /**
     * 默认private
     */
    Singleton2() {
        System.out.println("private Singleton2()");
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }
}

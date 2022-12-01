package org.hgd.interview;

import groovy.lang.GroovyShell;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

// -XX:MaxMetaspaceSize=24m
// 模拟不断生成类, 但类无法卸载的情况
public class TestOomTooManyClass {
// 静态变量不能被回收, 根节点
//    static GroovyShell shell = new GroovyShell();

    public static void main(String[] args) {
        AtomicInteger c = new AtomicInteger();
        while (true) {
            try (FileReader reader = new FileReader("script")) {
                GroovyShell shell = new GroovyShell();
                shell.evaluate(reader);
                System.out.println(c.incrementAndGet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package org.hgd.interview.loader;

import java.io.IOException;

public class TestFinal {
    public static void main(String[] args) throws IOException {
        // c 是 final static 基本类型
        System.out.println(Student.C);
        System.in.read();

        // m 是 final static 基本类型
        System.out.println(Student.m);
        System.in.read();

        // n 是 final static 引用类型
        System.out.println(Student.n);
        System.in.read();
    }
}

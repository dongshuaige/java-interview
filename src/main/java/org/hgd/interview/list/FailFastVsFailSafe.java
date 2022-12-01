package org.hgd.interview.list;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author：hgd
 * @date: 2022/11/17
 */
public class FailFastVsFailSafe {
    /**
     * fail-fast : 一旦发现遍历的同时，其他人来修改，则立刻抛出异常
     */
    public static void failFast() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A"));
        list.add(new Student("B"));
        list.add(new Student("C"));
        list.add(new Student("D"));
        for (Student student : list) {
            System.out.println(student.name);
        }
        System.out.println(list);
    }

    /**
     * fail-safe : 发现遍历的同时，其他人来修改，应当有应对策略，例如牺牲一致性来让整个遍历运行完成
     */
    public static void failSafe() {
        CopyOnWriteArrayList<Student> list = new CopyOnWriteArrayList<>();
        list.add(new Student("A"));
        list.add(new Student("B"));
        list.add(new Student("C"));
        list.add(new Student("D"));
        for (Student student : list) {
            System.out.println(student.name);
        }
        System.out.println(list);
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        failSafe();
    }

}



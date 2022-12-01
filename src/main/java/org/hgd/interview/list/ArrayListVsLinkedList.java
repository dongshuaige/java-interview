package org.hgd.interview.list;

import org.openjdk.jol.info.ClassLayout;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @description:
 * @author：hgd
 * @date: 2022/11/18
 */
// --add-opens java.base/java.lang=ALL-UNNAMED
// --add-opens java.base/java.util=ALL-UNNAMED
// --add-opens java.base/java.nio=ALL-UNNAMED
// --add-opens java.base/sun.nio.ch=ALL-UNNAMED
public class ArrayListVsLinkedList {
    public static void main(String[] args) {
        int n = 1000;
        for (int i = 0; i < 5; i++) {
            int[] array = randomArray(n);
            ArrayList<Integer> list = (ArrayList<Integer>) Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> list1 = new LinkedList<>(list);
            // 随机访问性能 ArrayList>LinkedList
//            randomAccess(list,list1,n/2);
            // 头插法性能比较 ArrayList<LinkedList
//            addFirst(list, list1);
            // 中间插入 ArrayList>LinkedList 如果要插入则一定要查询 很明显ArrayList查询要快于LinkedList，虽然ArrayList在插入时 会往后整体移动数组，但还是优于LinkedList的查询速度
//            addMiddle(list, list1, n / 2);
            //尾部插入时ArrayList的速度与LinkedList的速度差不多
//            addLast(list, list1);
            arrayListSize(list);
            linkedListSize(list1);
        }
    }

    /**
     * 中间插入
     */
    private static void addMiddle(ArrayList<Integer> array, LinkedList<Integer> linked, int mid) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        array.add(mid, 100);
        sw.stop();

        sw.start("LinkedList");
        linked.add(mid, 100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    /**
     * 头部插入性能比较
     */
    private static void addFirst(ArrayList<Integer> array, LinkedList<Integer> linked) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        array.add(0, 100);
        sw.stop();

        sw.start("LinkedList");
        linked.addFirst(100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    /**
     * 尾插法
     */
    private static void addLast(ArrayList<Integer> array, LinkedList<Integer> linked) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        array.add(100);
        sw.stop();

        sw.start("LinkedList");
        linked.addLast(100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    /**
     * 随机访问性能比较
     * 获取ArrayLis与LinkedList的查方法 执行时间
     */
    private static void randomAccess(ArrayList<Integer> list1, LinkedList<Integer> list2, int mid) {
        // spring的StopWatch类 用于获取程序执行时间
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        list1.get(mid);
        sw.stop();

        sw.start("LinkedList");
        list2.get(mid);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

   private static void linkedListSize(LinkedList<Integer> list) {
        try {
            long size = 0;
            ClassLayout layout = ClassLayout.parseInstance(list);
//            System.out.println(layout.toPrintable());
            size += layout.instanceSize();
            Field firstField = LinkedList.class.getDeclaredField("first");
            firstField.setAccessible(true);
            Object first = firstField.get(list);
//            System.out.println(ClassLayout.parseInstance(first).toPrintable());
            long nodeSize = ClassLayout.parseInstance(first).instanceSize();
            size += (nodeSize * (list.size() + 2));
            long elementSize = ClassLayout.parseInstance(list.getFirst()).instanceSize();
            System.out.println("LinkedList size:[" + size + "],per Node size:[" + nodeSize + "],per Element size:[" + elementSize * list.size() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static void arrayListSize(ArrayList<Integer> list) {
        try {
            long size = 0;
            ClassLayout layout = ClassLayout.parseInstance(list);
//            System.out.println(layout.toPrintable());
            size += layout.instanceSize();
            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            Object elementData = elementDataField.get(list);
//            System.out.println(ClassLayout.parseInstance(elementData).toPrintable());
            size += ClassLayout.parseInstance(elementData).instanceSize();
            long elementSize = ClassLayout.parseInstance(list.get(0)).instanceSize();
            System.out.println("ArrayList size:[" + size + "],array length:[" + list.size() + "],per Element size:[" + elementSize * list.size() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] randomArray(int n) {
        int[] array = new int[n];
        for (int j = 0; j < n; j++) {
            array[j] = (int) (Math.random() * n);
        }
        return array;
    }
}

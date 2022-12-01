package org.hgd.interview.algorithm.sort;

import java.util.Arrays;

/**
 * @description: 选择排序
 * @author：hgd
 * @date: 2022/11/8
 */
public class Selection {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 4, 1, 3, 8, 9};
        selection(arr);
    }

    /**
     * <p>1. 将数组分为两个子集,排序的和未排序的,每一轮从未排序的子集中选出最小的元素,放入排序子集</p>
     * <p>2. 重复以上步骤,直到整个数组有序</p>
     *
     * @param a 数组
     */
    public static void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            // i 表示每轮选择最小元素要交换到的目标索引
            // s 代表最小元素的索引
            int s = i;
            for (int j = s + 1; j < a.length; j++) {
                if (a[s] > a[j]) {
                    // 记录最小元素的索引值
                    s = j;
                }
            }
            // 大大减少元素的交换次数,保证每轮选择最多经过一次交换
            if (s != i) {
                swap(a, s, i);
            }
            System.out.println(Arrays.toString(a));
        }
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

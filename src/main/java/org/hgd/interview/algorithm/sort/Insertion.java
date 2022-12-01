package org.hgd.interview.algorithm.sort;

import java.util.Arrays;

/**
 * @description: 插入排序
 * @author：hgd
 * @date: 2022/11/15
 */
public class Insertion {
    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        insertion(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * <h2>插入排序(升序)</h2>
     * <p>1.将数组分为两个区域,排序区域和未排序区域,每一轮从未排序区域中取出第一个元素,插入到排序区域(需保证顺序)</p>
     * <p>2.重复以上步骤,直到整个数组有序</p>
     * @param a 数组arr
     */
    public static void insertion(int[] a) {
        // i 代表待插入元素的索引
        for (int i = 1; i < a.length; i++) {
            //定义待插入元素t
            int t = a[i];
            // j代表已排序区域的元素索引
            int j = i - 1;
            while (j >= 0) {
                //比较j的值与待插入的值
                //待插入的值<索引j的值,则j的值向后移一位,即j+1
                if (t < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    // 退出循环,减少比较的次数
                    break;
                }
                j--;
            }
            // 把待插入的值放入到空位中
            a[j+1] = t;
            System.out.println(Arrays.toString(a));
        }
    }
}

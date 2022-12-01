package org.hgd.interview.algorithm.sort;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @author：hgd
 * @date: 2022/11/6
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {5, 2, 7, 4, 1, 3, 8, 9};
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        bubbleSortV2(arr);
    }

    /**
     * 冒泡排序 1
     * <ol>
     *   <li>
     *     依次比较数组中相邻的两个元素大小,若a[j]>a[j+1],则交换两个元素,两两都比较一遍称为一轮冒泡,结果是让最大的元素排至最后
     *   </li>
     *   <li>
     *     重复以上步骤,直到整个数组有序
     *   </li>
     * </ol>
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 是否发生交换
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                System.out.println("比较次数:" + (j + 1));
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            System.out.println("第" + (i + 1) + "轮冒泡:" + Arrays.toString(arr));
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 冒泡排序 2
     * <p>
     *   优化方式: <b>每轮冒泡时,最后一次交换索引可以作为下一轮冒泡的比较次数,如果这个值为0,表示整个数组有序,直接退出外层循环即可</b>
     * </p>
     */
    public static void bubbleSortV2(int[] arr) {
        // 比较次数
        int n = arr.length - 1;
        do {
            // 最后一次交换索引位置
            int last = 0;
            for (int j = 0; j < n; j++) {
                System.out.println("比较次数:" + (j + 1));
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    last = j;
                }
            }
            // 把最后一次交换的索引位置作为新的循环次数
            n = last;
            System.out.println("冒泡:" + Arrays.toString(arr));
        } while (n != 0);
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

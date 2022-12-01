package org.hgd.interview.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hgd.interview.algorithm.sort.Selection.swap;


/**
 * @description: 双边循环快排
 * @author：hgd
 * @date: 2022/11/15
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        quick(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        ArrayList<String> list = new ArrayList<>();
    }

    /**
     * 双边循环快排--递归方式的实现
     *
     * @param a arr数组
     * @param l low->分区的下边界
     * @param h high->分区的上边界
     */
    public static void quick(int[] a, int l, int h) {
        //当low>=high时，说明分区内只有1个或没有元素，此时分区结束，排序完成
        if (l >= h) {
            return;
        }
        //调用排序方法开始分区
        //拿到下一轮分区的边界
        int p = partition(a, l, h);
        //上一轮轮分区结束后，又开始下一轮分区，使用递归的方式完成
        // 左边分区的范围确定
        quick(a, l, p - 1);
        // 右边分区的范围确定
        quick(a, p + 1, h);
    }

    /**
     * @param a arr数组
     * @param l low->分区的下边界
     * @param h high->分区的上边界
     * @return 基准点元素所在的正确索引
     */
    private static int partition(int[] a, int l, int h) {
        //1.以最左边元素作为基准点
        int pv = a[l];
        int i = l;
        int j = h;
        while (i < j) {
            //注意:必须先从右往左找小 再从左往右找大
            // 2.1  j指针负责从右向左找比基准点小的元素
            while (i < j && a[j] > pv) {
                j--;
            }
            // 2.2 i指针负责从左向右找比基准点大的元素
            while (i < j && a[i] <= pv) {
                i++;
            }
            swap(a, i, j);
        }
        // 最后基准点与i/j(此时i与j相等)交换
        swap(a, l, j);
        return j;
    }

}

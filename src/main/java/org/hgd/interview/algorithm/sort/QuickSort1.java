package org.hgd.interview.algorithm.sort;

import java.util.Arrays;

import static org.hgd.interview.algorithm.sort.Selection.swap;

/**
 * @description: 单边循环快排
 * @author：hgd
 * @date: 2022/11/15
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        quick(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 单边循环快排--递归方式的实现
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
        //1.以最右边元素作为基准点元素
        int pv = a[h];
        //2.i维护小于基准点元素的边界，也是每次交换的目标索引，从最左边的元素开始
        int i = l;
        //3.j负责找到比基准点小的元素，一旦找到就与i交换,同时i的索引也自然加1
        for (int j = i; j < h; j++) {
            if (a[j] < pv) {
                //当i=j时,我们再交换其实没有意义，所以可以优化一下,减少一次交换
                if (i != j) {
                    swap(a, i, j);
                }
                i++;
            }
        }
        //基准点所在的元素与i交换
        System.out.println(Arrays.toString(a) + " i=" + i);
        //4.最后基准点与i交换，i即为分区位置,至此一轮分区结束
        //同样的当i=h时交换没有意义，优化一下，减少一次交换
        if (i != h) {
            swap(a, h, i);
        }
        //返回值代表基准点元素所在的正确索引,用它确定下一轮分区的上下边界
        return i;
    }
}

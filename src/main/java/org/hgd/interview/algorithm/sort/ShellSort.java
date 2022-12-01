package org.hgd.interview.algorithm.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = {9, 3, 7, 2, 5, 8, 1, 4};
        shell(array);
    }

    /**
     * <ol>
     *   <li>首先选取一个间隙序列，如 (n/2，n/4 … 1)，n 为数组长度</li>
     *   <li>每一轮将间隙相等的元素视为一组，对组内元素进行插入排序，目的有二
     *     <ul>
     *        <li> ①少量元素插入排序速度很快</li>
     *        <li>② 让组内值较大的元素更快地移动到后方</li>
     *     </ul>
     *   </li>
     *   <li>当间隙逐渐减少，直至为 1 时，即可完成排序</li>
     * </ol>
     *
     * @param array 数组
     */
    private static void shell(int[] array) {
        int n = array.length;
        //gap 表示元素间的间隙
        for (int gap = n / 2; gap > 0; gap /= 2) {
            //i 表示待插入元素的索引
            for (int i = gap; i < n; i++) {
                //表示待插入的元素值
                int t = array[i];
                int j = i;
                while (j >= gap) {
                    //每次与上一个间隙为 gap 的元素进行插入排序
                    //array[j-gap] 是上一个元素,如果上一个元素大于待插入的元素值，则让它后移
                    if (t < array[j - gap]) {
                        array[j] = array[j - gap];
                        //下标移到 j-gap 的位置
                        j -= gap;
                    } else {
                        // 如果上一个元素小于等于待插入的元素值，则表示 j 就是插入位置
                        break;
                    }
                }
                array[i] = t;
                System.out.println(Arrays.toString(array) + "gap:" + gap);
            }
        }
    }
}
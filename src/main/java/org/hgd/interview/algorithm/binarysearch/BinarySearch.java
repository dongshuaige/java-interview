package org.hgd.interview.algorithm.binarysearch;

/**
 * @description: 二分查找
 * @author：hgd
 * @date: 2022/11/6
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int t = 48;
        int idx = binarySearch(arr, t);
        System.out.println(idx);
    }

    /**
     * 奇数二分取中间的值
     * 偶数二分取中间靠左的值
     * <ol>
     *   <li>前提有序数组A</li>
     *   <li>定义左边界L、R边界R,确定搜索范围,循环执行二分查找(3、4两步)</li>
     *   <li>获取中间索引M= Floor((L+R)/2)(会发生整数溢出)</li>
     *   <li>中间索引的值 A[M]待搜索的值T进行比较</li>
     *    <ul>
     *       <li>A[M]==T,表示找到,返回中间索引</p></li>
     *       <li>A[M]>T,中间值右侧的其他元素都大于T,无需比较,中间索引左边去找,M-1设置为右边界,重新查找</li>
     *       <li>A[M]"<"T,中间值左侧的其他元素都小于T,无需比较,中间索引右边去找,M+1设置为左边界,重新查找</li>
     *    </ul>
     *   <li>当L>R时,表示没有找到,应结束循环</li>
     * </ol
     *
     * @param a 有序数组
     * @param t 要找的元素
     * @return idx->要找的元素所在的索引
     */
    public static int binarySearch(int[] a, int t) {
        int l = 0, r = a.length - 1, m;
        while (l <= r) {
            //m=(l+r)/2(会发生整数溢出) ==>l/2+r/2==>l-l/2+r/2==>l+(-l/2+r/2)
            //解决整数溢出
            //方法一
            m = l + (r - l) / 2;
            // 方法二 m = (l+r)>>>1;
            if (a[m] == t) {
                return m;
            } else if (a[m] > t) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}

package com.lixm.animationdemo.other;

/**
 * Describe:二分法
 * <p>
 * Author: Lixm
 * Date: 2018/9/4
 */
public class AlgorithmTwo {

    public static void main(String args[]) {
        //             0, 1, 2, 3, 4
        int[] input = {3, 4, 5, 1, 2};
        System.out.println("最小值：" + getMin(input));
    }

    public static int getMin(int[] data) {
        if (data == null)
            throw new NullPointerException("input null");
        if (data.length == 1)
            return data[0];
        int result = data[0];
        int low = 0;
        int high = data.length - 1;
        int mid = low;
        while (data[low] >= data[high]) {
            if (high - low == 1) {
                return data[high];
            }
            //取中间位置
            mid = low + (high - low) / 2;
            //三值相等的特殊情况，则需从头到尾查找最小值
            if (data[mid] == data[low] && data[mid] == data[high]) {
                return midInorder(data, low, high);
            }
            //代表中间元素在左边递增子数组
            if (data[mid] >= data[low]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return result;
    }

    /**
     * 查找数组中的最小值
     *
     * @param nums  数组
     * @param start 数组开始位置
     * @param end   数组结束位置
     * @return 最小值
     */
    public static int midInorder(int[] nums, int start, int end) {
        int result = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (result >= nums[i]) {
                result = nums[i];
            }
        }
        return result;
    }

}

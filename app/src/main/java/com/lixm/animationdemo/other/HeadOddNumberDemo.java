package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/5
 */
public class HeadOddNumberDemo {

//    private static int[] reOrderArray(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            //遇到奇书就放到最前边
//            if ((Math.abs(arr[i]) & 0x1) == 1) {
//                int temp = arr[i];
//                //先把i前面的都向后移动一个位置
//                for (int j = i; j > 0; j--) {
//                    arr[j] = arr[j - 1];
//                }
//                arr[0] = temp;
//            }
//        }
//        return arr;
//    }

    interface ICheck {
        boolean function(int n);
    }

    public static class OrderEven implements ICheck {

        @Override
        public boolean function(int n) {
            return n % 2 == 0;
        }
    }

    private static int[] reOrderArray(int[] arr, ICheck iCheck) {
        int odd = 0, even = arr.length - 1;
        //循环结束的条件为odd>=even
        while (odd < even) {
            //第一个下标为偶数的时候停止
            while (odd < even && !iCheck.function(arr[odd])) {
                odd++;
            }
            //第二个下标为奇数的时候停止
            while (odd < even && iCheck.function(arr[even])) {
                even--;
            }
            //找到后对调两个值
            int temp = arr[odd];
            arr[odd] = arr[even];
            arr[even] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        OrderEven even = new OrderEven();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        arr = reOrderArray(arr,even);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arr1 = {2, 4, 6, 8, 1, 3, 5, 7, 9};
        arr1 = reOrderArray(arr1,even);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        int[] arr2 = {2, 4, 6, 8, 10};
        arr2 = reOrderArray(arr2,even);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }

}

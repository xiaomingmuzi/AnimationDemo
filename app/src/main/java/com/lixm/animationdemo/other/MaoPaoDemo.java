package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/19
 */
public class MaoPaoDemo {

    private static int n = 0;

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        n++;
    }

    private static void printArr(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    private static void insertionSort(int[] arr) {
        if (arr == null)
            return;
        int j;
        int temp;
        for (int i = 1; i < arr.length; i++) {
            //设置哨兵，拿出待插入的值
            temp = arr[i];
            j = i;
            //然后寻找正确插入的位置
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void bubbleSort(int[] arr) {
        if (arr == null)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void bubbleSort2(int[] arr) {
        if (arr == null)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] < arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    private static void bubbleSort3(int[] arr) {
        if (arr == null)
            return;
        //定义一个标记 isSort ，当其值为 true 的时候代表已经有序。
        boolean isSort;
        for (int i = 0; i < arr.length - 1; i++) {
            isSort = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    isSort = false;
                }
            }
            if (isSort)
                break;
        }
    }

    private static void bubbleSort4(int[] arr) {
        if (arr == null) {
            return;
        }
        int flag = arr.length;
        int k;
        for (int i = 0; i < arr.length - 1; i++) {
            k = flag;
            flag = 0;
            for (int j = 1; j < k; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    flag = j;
                }
            }
            if (flag == 0)
                break;
        }
    }

    private static void selectSort(int[] arr) {
        if (arr == null)
            return;
        int i, j, min, len = arr.length;
        for (i = 0; i < len - 1; i++) {
            min = i;//未排序的序列中最小元素的下标
            for (j = i + 1; j < len; j++) {
                //在未排序元素中继续寻找最小元素，并保存其下标
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {6, 4, 2, 1, 8, 3, 7, 9, 5};
//        int[] arr = {6, 4, 1, 2, 3, 5, 7, 8, 9};
//        int[] arr1 = {6, 4, 9, 8, 7, 5, 3, 2,1};
//        bubbleSort(arr);
//        bubbleSort2(arr);
//        bubbleSort3(arr);
//        bubbleSort4(arr);
//        printArr(arr);
//        System.out.println("进行了"+n+"次交换");
//        n=0;
//
//        bubbleSort4(arr1);
//        printArr(arr1);
//        System.out.println("进行了"+n+"次交换");

//        selectSort(arr);
        insertionSort(arr);
        printArr(arr);

    }
}

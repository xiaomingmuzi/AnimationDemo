package com.lixm.animationdemo.other;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Describe:斐波拉契函数
 * <p>
 * Author: Lixm
 * Date: 2018/9/4
 */
public class FibonacciDemo {

    public static void main(String args[]) {
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println(fn(20));
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println(fnn(100));
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println(fn(2));
//        System.out.println(fn(3));
//        System.out.println(fn(4));
//        System.out.println(fn(5));
//        System.out.println(fn(6));
//        System.out.println(fn(7));
//        System.out.println(fn(8));
//        System.out.println(fn(9));
//        System.out.println(fn(10));

        Node head = new Node();
        head.data = 1;
        head.next = new Node();
        head.next.data = 2;
        head.next.next = new Node();
        head.next.next.data = 3;
        head.next.next.next = new Node();
        head.next.next.next.data = 4;
        head.next.next.next.next = new Node();
        head.next.next.next.next.data = 5;
        printLinkReverse3(head);
    }

    public static int fn(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        else return fn(n - 1) + fn(n - 2);
    }

    private static long fnn(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        long prePre = 1, pre = 2;
        long result = 0;
        for (int i = 3; i <= n; i++) {
            result = prePre + pre;
            prePre = pre;
            pre = result;
        }
        return result;
    }

    public static class Node {
        int data;
        Node next;
    }

    public static void printLinkReverse(Node head) {
        ArrayList<Node> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head);
            head = head.next;
        }
        for (int i = nodes.size() - 1; i >= 0; i--) {
            System.out.print(nodes.get(i).data + " ");
        }
    }

    public static void printLinkReverse2(Node head) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().data + " ");
        }
    }

    public static void printLinkReverse3(Node head) {
        if (head != null) {
            printLinkReverse3(head.next);
            System.out.print(head.data + " ");
        }
    }
}

package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/17
 */
public class LinkFindDemo {
    public static class LinkNode {
        int data;
        LinkNode next;

        public LinkNode(int data) {
            this.data = data;
        }
    }

    private static int getTheMid(LinkNode head) {
        int count = 0;
        LinkNode node = head;
        while (head != null) {
            head = head.next;
            count++;
        }
        for (int i = 0; i < count / 2; i++) {
            node = node.next;
        }
        return node.data;
    }

    private static int getTheMid2(LinkNode head) {
        LinkNode slow = head;
        LinkNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    private static boolean isRingLink(LinkNode head) {

        LinkNode slow = head;
        LinkNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast || fast.next == slow) {
                return true;
            }
        }
//简化后可以如下写法，省略slow
//        LinkNode fast = head;
//        while (fast != null && fast.next != null) {
//            fast = fast.next.next;
//            head = head.next;
//            if (head == fast || fast.next == head) {
//                return true;
//            }
//        }

        return false;
    }

    private static int getSpecifiedNodeReverse(LinkNode head, int k) {
        LinkNode slow = head;
        LinkNode fast = head;
        if (fast == null) {
            throw new RuntimeException("your linkNode is null");
        }
        //先让fast先走k-1步
        for (int i = 0; i < k - 1; i++) {
            if (fast.next == null) {
                //说明输入的k已经超过了链表的长度，直接报错
                throw new RuntimeException("the value k is too large");
            }
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.data;
    }

    private static LinkNode delTheSpecifiedReverse(LinkNode head, int k) {
        LinkNode slow = head;
        LinkNode fast = head;
        if (fast == null) {
            throw new RuntimeException("your linkNode is null");
        }
        //先让fast先走k步
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                //说明输入的k已经超过了链表的长度，直接报错
                throw new RuntimeException("the value k is too large");
            }
            fast = fast.next;
        }
        //fast == null ,说明已经到了尾结点后边的空区域，说明要删除的是头结点
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private static LinkNode reverseLink(LinkNode head) {
        //上一个结点
        LinkNode nodePre = null;
        LinkNode next = null;
        LinkNode node = head;
        while (node!=null){
            //先用next保存下一个要反转的结点，不然会导致链表断裂.
            next=node.next;
            //在把现在结点的next引用指向上一个结点
            node.next=nodePre;
            //把当前结点赋值给nodePre变量，以便于下一次赋值
            nodePre=node;
            //向后遍历
            node=next;
        }
        return nodePre;
    }

    public static void main(String[] args) {
        LinkNode head = new LinkNode(1);
        head.next = new LinkNode(2);
        head.next.next = new LinkNode(3);
        head.next.next.next = new LinkNode(4);
        head.next.next.next.next = new LinkNode(5);
//        System.out.println("getTheMid2："+getTheMid2(head));
//        head.next.next.next.next.next = head;
//        System.out.println("isRingLink："+isRingLink(head));
//        System.out.println("getSpecifiedNodeReverse：" + getSpecifiedNodeReverse(head, 3));
//        System.out.println("getSpecifiedNodeReverse：" + getSpecifiedNodeReverse(null, 1));
//        System.out.println("未删除前链表为：");

//        LinkNode old = head;
//        while (old != null) {
//            System.out.print(old.data + "->");
//            old = old.next;
//        }
//        System.out.println("");
//        LinkNode node = delTheSpecifiedReverse(head, 3);
//        System.out.println("删除后链表为：");
//        while (node != null) {
//            System.out.print(node.data + "->");
//            node = node.next;
//        }

        LinkNode node=reverseLink(head);
        while (node!=null){
            System.out.print(node.data+"->");
            node=node.next;
        }
    }
}

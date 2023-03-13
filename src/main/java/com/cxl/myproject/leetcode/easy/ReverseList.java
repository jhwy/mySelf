package com.cxl.myproject.leetcode.easy;

/**
 * @author cxl
 * @date 2023/3/13 & 17:55
 */
public class ReverseList {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        System.out.println(reverseList(listNode).toString());
        System.out.println(reverseRecurrence(listNode).toString());
    }

    /**
     * 链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur !=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode reverseRecurrence(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = reverseRecurrence(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


package com.cxl.myproject.leetcode.hard;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。

 返回 滑动窗口中的最大值 。

 

 示例 1：

 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 输出：[3,3,5,5,6,7]
 解释：
 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 示例 2：

 输入：nums = [1], k = 1
 输出：[1]
 

 提示：

 1 <= nums.length <= 105
 -104<= nums[i] <= 104
 1 <= k <= nums.length

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/sliding-window-maximum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxSlidingWindow {

    public static void main(String[] args) {
       int[] nums = new int[]{8,3,-1,-4,5,3,6,7};
        int[] ints = maxSlidingWindowV2(nums, 3);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }


    /**
     *  优先队列最大堆
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //最大堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] num1, int[] num2) {
                return num1[0] != num2[0] ? num2[0] - num1[0] : num2[1] - num1[1];
            }
        });

        //前k个数据中最大值
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i],i});
        }

        int[] ans = new int[n-k+1];
        ans[0] = pq.peek()[0];

        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i],i});
            while (pq.peek()[1] <= i-k){
                pq.poll();
            }
            ans[i-k+1] = pq.peek()[0];
        }
        return ans;
    }


    /**
     *  双向队列
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowV2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList();

        for (int i = 0; i < k; ++i) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n-k+1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            ans[i-k+1] = nums[deque.peekFirst()];
        }
        return ans;
    }

}


package com.cxl.myproject.leetcode.medium;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author cxl
 * @date 2023/3/14 & 14:01
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class FindKthLargest {

    /**
     *  最小堆
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums,int k){
        int l = nums.length;
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <l; i++) {
            pq.offer(nums[i]);

            if(pq.size()>k){
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     *  快排
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestFastSort(int[] nums,int k){
      return 0;
    }

    public static int findKthLargestSelectSort(int[] nums,int k){
        for (int i = 0; i < k; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]<nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums[k-1];
    }

    public static void insertSort(int[] nums){
        int count=0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if(nums[j]<nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }else{
                    break;
                }
                count++;
            }

        }
        System.out.println("insertSort cycle count:"+count);
    }

    public static void selectSort(int[] nums){
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            int minIdx = i;
            System.out.println("i:"+i);
            boolean flag = true;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j]<nums[minIdx]){
                    minIdx = j;
                    flag = false;
                }
                count++;
            }
            if(flag){
                System.out.println("selectSort  break count:"+count);
                break;
            }
            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }
        System.out.println("selectSort cycle count:"+count);
    }

    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

    public static void popSort(int[] nums){
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            boolean flag=true;
            for (int j = 1; j < nums.length-i; j++) {
                if(nums[j-1]>nums[j]){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    flag = false;
                }
                count++;
            }
            if(flag){
                break;
            }
        }
        System.out.println("popSort cycle count:"+count);
    }

    public static void quickSort(int[] nums,int l,int r){
        if(l>r){
            return;
        }
        int i=l;
        int j=r;
        int b = nums[l];
        while (i<j){
            while (i<j && nums[j] >= b){
                j--;
            }
            if(i<j){
                nums[i++] =nums[j];
            }
            while (i<j && nums[i] < b){
                i++;
            }
            if(i<j){
                nums[j--] = nums[i];
            }
        }
        nums[i] = b;
        quickSort(nums,l,i-1);
        quickSort(nums,i+1,r);
        return;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,1,8,4,5,6,2,4,7,9};
        long start = System.nanoTime();
//        quickSort(nums,0,nums.length-1);
//        selectSort(nums);
//        System.out.println(findKthLargestSelectSort(nums, 2));
//        popSort(nums);
//        insertSort(nums);
        shellSort(nums);
        long end = System.nanoTime();
        System.out.println(JSON.toJSON(nums));
        long l = end - start;
        System.out.println("time :"+ l);
    }

}

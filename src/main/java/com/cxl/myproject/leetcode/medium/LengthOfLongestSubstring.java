package com.cxl.myproject.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cxl
 * @date 2023/3/13 & 18:15
 */
public class LengthOfLongestSubstring {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 提示：
     *
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */


    public static void main(String[] args) {
        String str = "abcabcbb";
        String str1 = "bbbbb";
        String str2 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(lengthOfLongestSubstring(str1));
        System.out.println(lengthOfLongestSubstring(str2));
    }

    public static int lengthOfLongestSubstring(String s){
        int l = s.length();
        int ans = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int end = 0,start=0; end < l; end++) {
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start = Math.max(map.get(c),start);
            }
            ans = Math.max(end-start+1,ans);
            map.put(s.charAt(end),end+1);
        }
        return ans;
    }




}

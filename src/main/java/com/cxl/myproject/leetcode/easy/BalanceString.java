package com.cxl.myproject.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author cxl
 * @date 2023/3/13 & 9:21
 */
public class BalanceString {
    /**
     * 给你一个字符串s，它仅包含字符'a' 和'b' 。
     *
     * 你可以删除s中任意数目的字符，使得s 平衡。当不存在下标对(i,j)满足i < j ，且s[i] = 'b' 的同时s[j]= 'a' ，此时认为 s 是 平衡 的。
     *
     * 请你返回使 s平衡的 最少删除次数。
     *
     * 
     *
     * 示例 1：
     *
     * 输入：s = "aababbab"
     * 输出：2
     * 解释：你可以选择以下任意一种方案：
     * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
     * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
     * 示例 2：
     *
     * 输入：s = "bbaaaaabb"
     * 输出：2
     * 解释：唯一的最优解是删除最前面两个字符。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        char[] chars = {'a', 'b'};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int l = random.nextInt(18)+2;
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < l; j++) {
                int idx = random.nextInt(chars.length);
                sb.append(chars[idx]);
            }
            System.out.println(minimumDeletions(sb.toString()));
        }


        List list = new ArrayList();

        LinkedList<Object> objects = new LinkedList<>();

    }


    /**
     *  前缀后缀分割法
     * @param s
     * @return
     */
    public static int minimumDeletions(String s){
        System.out.println(s);
        int del = 0;
        for (char c : s.toCharArray()) {
            del += 'a'-c;
        }
        int n = del;
        for (char c : s.toCharArray()){
            del += (c-'b')*2-1;
            n = Math.min(n,del);
        }
        return n;
    }

    /**
     *  动态规划
     *  结尾b  f[i] = f[i-1]
     *  结尾a  f[i] = Min(f[i-1]+1,cntB)
     * @param s
     * @return
     */
    public static int minimumDeletionsD(String s){
        int ans = 0,cntB = 0;
        for (char c : s.toCharArray()) {
            if(c== 'b') ++cntB;
            else ans = Math.min(ans+1,cntB);
        }

        return ans;
    }

}

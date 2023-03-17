package com.cxl.myproject.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cxl
 * @date 2023/3/14 & 9:21
 */
public class LRUCache extends LinkedHashMap<Integer, Integer>{
    /**
     *  方案 1
     */
//    int cap;
//    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
//
//    public LRUCache(int capacity) {
//        this.cap = capacity;
//    }
//
//    public int get(int key) {
//        if (!cache.containsKey(key)) {
//            return -1;
//        }
//        makeRecently(key);
//        return cache.get(key);
//    }
//
//    public void put(int key, int value) {
//        if(cache.containsKey(key)){
//            cache.put(key,value);
//            makeRecently(key);
//            return;
//        }
//
//        if(cache.size() >=this.cap){
//            int oldestKey =cache.keySet().iterator().next();
//            cache.remove(oldestKey);
//        }
//        cache.put(key,value);
//
//
//    }
//
//    private void makeRecently(int key) {
//        int val = cache.get(key);
//        // 删除 key，重新插入到队尾
//        cache.remove(key);
//        cache.put(key, val);
//    }


    private int capacity;
    public LRUCache(int capacity) {
        super(capacity);
    }

    public int get(int key) {
        return (int) super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }



    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }



    public static void main(String[] args) {
        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String, Integer>();
        lmap.put("语文", 1);
        lmap.put("数学", 2);
        lmap.put("英语", 3);
        lmap.put("历史", 4);
        lmap.put("政治", 5);
        lmap.put("地理", 6);
        lmap.put("生物", 7);
        lmap.put("化学", 8);
        for(Map.Entry<String, Integer> entry : lmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}

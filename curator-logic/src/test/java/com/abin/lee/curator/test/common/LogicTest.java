package com.abin.lee.curator.test.common;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Created by abin on 2017/11/23 20:21.
 * curator-svr
 * com.abin.lee.curator.logic.test
 */
public class LogicTest {

    public static void main(String[] args) {
        Map<String, Integer> frequence1 = Maps.newHashMap();
        frequence1.put("abin10", 10);
        frequence1.put("abin6", 6);
        frequence1.put("abin8", 8);
        frequence1.put("abin7", 7);
        frequence1.put("abin9", 9);
        System.out.println("-------------------------------------");

        String result = get(frequence1, 2);
        System.out.println("result=" + result);

    }

    @Test
    public void test1() {
        Map<String, Integer> frequence = Maps.newHashMap();
        frequence.put("abin10", 10);
        frequence.put("abin6", 6);
        frequence.put("abin3", 3);
        frequence.put("abin8", 8);
//        frequence.put("abin1", 1);
//        frequence.put("abin2", 2);
//        frequence.put("abin4", 4);
//        frequence.put("abin7", 7);
//        frequence.put("abin5", 5);
//        frequence.put("abin9", 9);
        System.out.println("-------------------------------------");

        Map<String, Integer> result = get1(frequence, 5);
        System.out.println("result=" + result);
    }


    public static Map<String, Integer> get1(Map<String, Integer> frequence1, Integer num) {
        Map<String, Integer> result = sortByValue(frequence1);
        Map<String, Integer> result1 = Maps.newLinkedHashMap();
        if (MapUtils.isNotEmpty(result)) {

            if (result.size() >= num) {
                AtomicInteger increase = new AtomicInteger(0);
                for (Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, Integer> entry = iterator.next();
                    if (increase.get() == num) {
                        return result1;
                    } else {
                        result1.put(entry.getKey(), entry.getValue());
                        increase.getAndIncrement();
                    }
                }
            } else {
                for (Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, Integer> entry = iterator.next();
                    result1.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return result1;
    }


    public static String get(Map<String, Integer> frequence1, Integer num) {
        Map<String, Integer> result1 = sortByValue(frequence1);
        AtomicInteger increase = new AtomicInteger(0);
        String param = "";
        for (Iterator<Map.Entry<String, Integer>> iterator = result1.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
            if (frequence1.size() >= num) {
                if (increase.get() == num) {
                    param = entry.getKey();
                    return param;
                } else {
                    increase.getAndIncrement();
                }
            }
        }
        return null;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        //        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        st.sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }


}

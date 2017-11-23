package com.abin.lee.curator.common.test;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by abin on 2017/11/23 18:40.
 * algorithm-svr
 * com.abin.lee.algorithm.basis.test
 */
public class BasisTest {

    public static void main(String[] args) {
        String param = " ";
        boolean flag = Strings.isNullOrEmpty(param);
        System.out.println("flag= "+flag);

        Map<String, Integer> frequence = Maps.newHashMap();
        frequence.put("abin10", 5);
        frequence.put("abin6", 2);
        frequence.put("abin3", 8);
        frequence.put("abin8", 7);
        Map<String, Integer> result = sortByValue(frequence);
        for(Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("key="+entry.getKey() + ", value="+entry.getValue());
        }
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

//        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        st.sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }


}

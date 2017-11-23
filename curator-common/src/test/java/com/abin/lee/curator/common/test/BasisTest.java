package com.abin.lee.curator.common.test;

import com.abin.lee.curator.svr.common.JsonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

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
        frequence.put("abin10", 10);
        frequence.put("abin6", 6);
        frequence.put("abin3", 3);
        frequence.put("abin8", 8);
        frequence.put("abin1", 1);
        frequence.put("abin2", 2);
        frequence.put("abin4", 4);
        frequence.put("abin7", 7);
        frequence.put("abin5", 5);
        frequence.put("abin9", 9);
        Map<String, Integer> result = sortByValue(frequence);
        for(Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("key="+entry.getKey() + ", value="+entry.getValue());
        }

        Map<String, Integer> frequence1 = Maps.newHashMap();
        frequence1.put("abin10", 10);
        frequence1.put("abin6", 6);
        frequence1.put("abin8", 8);
        frequence1.put("abin7", 7);
        frequence1.put("abin9", 9);
        System.out.println("-------------------------------------");

        Map<String, Integer> result1 = sortByValue(frequence1);
        for(Iterator<Map.Entry<String, Integer>> iterator = result1.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("key="+entry.getKey() + ", value="+entry.getValue());
        }

        for(Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, Integer> entry = iterator.next();
            for(Iterator<Map.Entry<String, Integer>> iterator1 = result1.entrySet().iterator(); iterator1.hasNext();){
                Map.Entry<String, Integer> entry1 = iterator1.next();
//                System.out.println("key="+entry.getKey() + ", value="+entry.getValue());
                if(StringUtils.equals(entry.getKey(),entry1.getKey()))
                    iterator.remove();
            }
        }

        System.out.println("result====="+ JsonUtil.toJson(result));


    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

//        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        st.sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }


}

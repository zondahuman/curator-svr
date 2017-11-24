package com.abin.lee.curator.svr.common;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by abin on 2017/11/23 2017/11/23.
 * curator-svr
 * com.abin.lee.curator.svr.common
 */
public class MapUtil {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

//        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
        st.sorted((c1,c2)-> c2.getValue().compareTo(c1.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }


}

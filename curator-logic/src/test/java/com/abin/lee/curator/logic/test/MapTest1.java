package com.abin.lee.curator.logic.test;

import com.abin.lee.curator.logic.test.enums.OrderEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by abin on 2017/11/24 11:27.
 * curator-svr
 * com.abin.lee.curator.logic.test
 */
public class MapTest1 {



    @Test
    public void test1(){
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
    }


    public String mapForeach1(Map<String, Integer> items){
        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
                return;
            }
        });
        return StringUtils.EMPTY;
    }

    public String mapForeach2(Map<String, Integer> items){
        items.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
        return StringUtils.EMPTY;
    }

    @Test
    public void listForeach2(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6);
        list.stream().forEach(item ->{
            System.out.println("item : " + item);
            if(item.equals(2)){
                System.out.println("item--jump=" + item);
                return;
            }
        });
    }

    @Test
    public void test2(){
        String param = "abcdef";
        System.out.println(param.substring(0,1));
    }


    @Test
    public void test3(){
        Map<String, Integer> demands = new LinkedHashMap<>();
        demands.put("A", 10);
        demands.put("B", 20);
        demands.put("C", 30);
        demands.put("D", 40);
        demands.put("E", 50);
        demands.put("F", 60);
        System.out.println("demands=" + demands);
        Map<String, Integer> kill = new LinkedHashMap<>();
        kill.put("A", 10);
        kill.put("B", 20);
        System.out.println("kill=" + kill);
        for (Iterator<Map.Entry<String, Integer>> iterator = demands.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Integer> entry = iterator.next();
            if(kill.containsKey(entry.getKey()))
                iterator.remove();
        }
        System.out.println("demands=" + demands);
    }

    @Test
    public void test4(){
        Map<String, Integer> demands = new LinkedHashMap<>();
        demands.put("A", 10);
        demands.put("B", 20);
        demands.put("C", 30);
        demands.put("D", 40);
        demands.put("E", 50);
        demands.put("F", 60);
        System.out.println("demands=" + demands);
        Map<String, Integer> result = filterFormer(demands,  8);
        System.out.println("result=" + result);
    }

    public Map<String, Integer> filterFormer(Map<String, Integer> demands, Integer num){
        if(MapUtils.isEmpty(demands))
            return null;
        Map<String, Integer> frequencyResultMap = Maps.newLinkedHashMap();
        if (MapUtils.isNotEmpty(demands)) {
//            if (demands.size() >= num) {
                AtomicInteger increase = new AtomicInteger(0);
                for (Iterator<Map.Entry<String, Integer>> iterator = demands.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, Integer> entry = iterator.next();
                    if (increase.get() <= num) {
                        frequencyResultMap.put(entry.getKey(), entry.getValue());
                        increase.getAndIncrement();
                    }
                }
//            }
        }
        return frequencyResultMap;
    }

    @Test
    public void test5(){
        String result = getOrder(OrderEnum.ONE);
        System.out.println("result=" + result);
    }

    public String getOrder(OrderEnum orderEnum){
        switch(orderEnum){
            case ONE:
                return "1";
            case TWO:
                return "2";
            case THREE:
                return "3";
            default:
                return "";
        }
    }

}

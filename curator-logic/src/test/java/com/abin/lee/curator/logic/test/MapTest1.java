package com.abin.lee.curator.logic.test;

import com.abin.lee.curator.logic.test.enums.OrderEnum;
import com.abin.lee.curator.svr.common.DateUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
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

    @Test
    public void test6(){
        List<Detail> detailList = Lists.newArrayList();
        Detail detail1 = new Detail();
        detail1.setLonger(101);
        detail1.setMobile("13300009999");
        detail1.setStartTime(DateUtil.getYMDHMSTime("2017-10-1 12:00:01"));
        detailList.add(detail1);
        Detail detail2 = new Detail();
        detail2.setLonger(102);
        detail2.setMobile("13300009991");
        detail2.setStartTime(DateUtil.getYMDHMSTime("2017-10-5 12:00:01"));
        detailList.add(detail2);
        Detail detail3 = new Detail();
        detail3.setLonger(103);
        detail3.setMobile("13300009992");
        detail3.setStartTime(DateUtil.getYMDHMSTime("2017-9-25 12:00:01"));
        detailList.add(detail3);

        Detail detail4 = new Detail();
        detail4.setLonger(104);
        detail4.setMobile("13300009999");
        detail4.setStartTime(DateUtil.getYMDHMSTime("2017-10-1 12:00:05"));
        detailList.add(detail4);
        Detail detail5 = new Detail();
        detail5.setLonger(105);
        detail5.setMobile("13300009999");
        detail5.setStartTime(DateUtil.getYMDHMSTime("2017-10-1 12:00:45"));
        detailList.add(detail5);

        Map<String, Integer> result = getStatics(detailList);
        System.out.println("result=" + result);
        Map<String, Integer> result1 = getLonger(detailList);
        System.out.println("result1=" + result1);

    }


    public Map<String, Integer> getStatics(List<Detail> detailList) {
        Map<String, Integer> frequencyMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(detailList)) {
            Integer total = null;
            for (Detail temp : detailList) {
                String toMobile = temp.getMobile();
                total = frequencyMap.get(toMobile);
                if (null != total) {
                    frequencyMap.put(toMobile, ++total);
                } else {
                    frequencyMap.put(toMobile, total == null ? 1 : total);
                }
            }
        }
        return frequencyMap;
    }

    public Map<String, Integer> getLonger(List<Detail> detailList) {
        Map<String, Integer> frequencyMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(detailList)) {
            Integer total = null;
            for (Detail temp : detailList) {
                String toMobile = temp.getMobile();
                Integer duration = temp.getLonger();
                if (!Strings.isNullOrEmpty(toMobile)) {
                    total = frequencyMap.get(toMobile);
                    if (null != total) {
                        frequencyMap.put(toMobile, (total + duration));
                    } else {
                        frequencyMap.put(toMobile, duration);
                    }
                }
            }
        }
        return frequencyMap;
    }
    @Test
    public void test7(){
        Map<String, Integer> demands = new LinkedHashMap<>();
        demands.put("A", 10);
        demands.put("B", 20);
        demands.put("C", 30);
        demands.put("D", 40);
        demands.put("E", 50);
        demands.put("F", 60);
        System.out.println("demands=" + demands);
        String result = getSpecialItem(demands,  2);
        System.out.println("result=" + result);
    }

    public String getSpecialItem(Map<String, Integer> frequencyResultMap, Integer num){
        if (MapUtils.isNotEmpty(frequencyResultMap)) {
            if (frequencyResultMap.size() >= num) {
                AtomicInteger increase = new AtomicInteger(1);
                for (Iterator<Map.Entry<String, Integer>> iterator = frequencyResultMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, Integer> entry = iterator.next();
                    if (increase.get() == num) {
                        return entry.getKey();
                    } else {
                        increase.getAndIncrement();
                    }
                }
            }
        }
        return StringUtils.EMPTY;
    }

}

@Getter
@Setter
class Detail {
    private String mobile;
    private Integer longer;
    private Date startTime;



}
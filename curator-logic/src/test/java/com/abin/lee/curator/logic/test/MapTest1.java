package com.abin.lee.curator.logic.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

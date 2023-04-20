package com.sriram.estimate.controllers;

import com.sriram.estimate.repository.entity.Business;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("123","Sri ram");
        map.put("124","ram");
        map.put("123","prasad");
        System.out.println(map.get("123"));
        Map<String, Business> businessMap = new HashMap<>();

        for(Map.Entry<String,String > entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+ entry.getValue());
        }
        List<String> names = new ArrayList<>();
        names.add("Sri ram");
        names.add("Arkay");
        names.add("ram");
        names.add("veeru");
        names.stream().forEach(System.out::println);
        Collections.sort(names);
        names.stream().forEach(System.out::println);
        System.out.println("==================");
        Iterator<String> it = names.iterator();
        while (it.hasNext()){
            //System.out.println(it.next());
            if(it.next().equalsIgnoreCase("ram")){
                it.remove();
            }
        }
        names.stream().forEach(System.out::println);

    }
}


// [1,2,3,4]

package com.github.springbootmonitor.common;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/19 0019 16:16
 */
public class ReflectUtils{

    public static<T> String[] getProperties(T obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> fieldNames=new ArrayList<>();
        try {
            for(Field field : fields){
                field.setAccessible(true);
                Object val = field.get(obj);
                if (val instanceof Map){
                    for(Object x:((Map) val).keySet()){
                        fieldNames.add(field.getName()+"_"+x.toString());
                    }
                }else{
                    fieldNames.add(field.getName());
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldNames.toArray(new String[0]);
    }

    public static<T> Map<String, Object> getKeyValueMap(T obj){
        Map<String,Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(Field field : fields){
                field.setAccessible(true);
                Object val = field.get(obj);
                if (val instanceof Map){
                    for(Object x:((Map) val).keySet()){
                        map.put(field.getName()+"_"+x.toString(), ((Map) val).get(x).toString());
                    }
                } else {
                    map.put(field.getName(), field.get(obj));
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}

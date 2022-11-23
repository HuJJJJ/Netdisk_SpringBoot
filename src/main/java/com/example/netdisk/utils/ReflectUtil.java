package com.example.netdisk.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtil {


    // 将一种类型转换为另一种类型
    public static  <T> T convertObject(Object oldObj, Class<T> newClass) throws Exception{
        // 判空
        if (null == oldObj) {
            throw new RuntimeException("对象不能为空！");
        }

        Class<?> oldClass = oldObj.getClass();
        Field[] oldFields = oldClass.getDeclaredFields();
        // 存放之前对象的属性值  key:属性名 value:属性值
        Map<String, Object> map = new HashMap<>(16);
        // 遍历之前对象的属性
        for (Field oldField : oldFields) {
            String fieldName = oldField.getName();
            // 获取某个属性的 get() 方法
            String strGet = "get" + fieldName.substring(0 , 1).toUpperCase() + fieldName.substring(1);
            Method method = oldClass.getMethod(strGet);
            // 通过调用 get() 方法来获取属性的值 o
            Object o = method.invoke(oldObj);
            // 将属性名和属性值放入 map 中
            map.put(fieldName, o == null ? "" : o);
        }

        // 调用新对象的空构造方法
        T newObj = newClass.newInstance();
        Field[] newFields = newClass.getDeclaredFields();
        for (Field newField : newFields) {
            newField.setAccessible(true);
            Class<?> fieldType = newField.getType();
            String fieldName = newField.getName();
            // 从 map 中获取字段的属性值
            String fieldValue = map.get(fieldName) == null ? null : map.get(fieldName).toString();

            if (null != fieldValue) {
                // 字段类型为 String
                if (String.class.equals(fieldType)) {
                    newField.set(newObj, fieldValue);
                    // 字段类型为 Integer
                } else if (Integer.class.equals(fieldType)) {
                    newField.set(newObj, Integer.valueOf(fieldValue));
                    // 字段类型为 double
                } else if (double.class.equals(fieldType)) {
                    newField.set(newObj, Double.parseDouble(fieldValue));
                }
            }
        }
        return newObj;
    }

}

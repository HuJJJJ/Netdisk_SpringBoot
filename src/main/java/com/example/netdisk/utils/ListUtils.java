package com.example.netdisk.utils;

import lombok.var;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;

@Service
public class ListUtils {

    /**
     * 按照数字排序
     **/
    public static List<String> ByNumberSort(List<String> strList) {
        String temp = "";
        for (int i = 0; i < strList.size() - 1; i++) {
            for (int j = 0; j < strList.size() - 1 - i; j++) {
                var current = split(strList.get(j));
                var next = split(strList.get(j + 1));
                if (current >next ) {
                    temp = strList.get(j);
                    strList.set(j,strList.get(j+1));
                    strList.set(j+1,temp);
                }
            }
        }
        return  strList;
    }

    private static int split(String str) {
        var tempArr = str.split("\\\\");
        var result = tempArr[tempArr.length - 1];
        return Integer.parseInt(result);
    }


}

package com.example.netdisk.utils;


import org.springframework.stereotype.Service;

@Service
public class ProgressUtils {

    public static double ExecPercent(double PassCount, double allCount)
    {
        double num = 0;
        if (allCount > 0)
        {
            num = ChinaRound((double)Math.round(PassCount / allCount * 100), 0);
        }
        return num;
    }
    private static double ChinaRound(double value, int decimals)
    {
        if (value < 0)
        {
            return Math.round(value + 5 / Math.pow(10, decimals + 1));
        }
        else
        {
            return Math.round(value);
        }
    }
}

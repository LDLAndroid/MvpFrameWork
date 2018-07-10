package com.zhixun.mvptest.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/4/10.
 */

public class FormatUtils {

    public static String formatStr(Object obj){
        if (obj != null && !obj.equals("")) {
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(obj);
        }else{
            return "0.00";
        }

    }

    public static String formatString(String str){
        if (str != null && !str.equals("")) {
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(Double.parseDouble(str));
        }else{
            return "0.00";
        }

    }
}

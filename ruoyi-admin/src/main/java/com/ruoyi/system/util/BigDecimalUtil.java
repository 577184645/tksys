package com.ruoyi.system.util;

import java.math.BigDecimal;

/**
 * Created by geely
 */
public class BigDecimalUtil {

    private BigDecimalUtil(){

    }


    public static BigDecimal add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }


    public static BigDecimal mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//四舍五入,保留2位小数

        //除不尽的情况
    }

    public static BigDecimal divint(double v1,int v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if(v2!=0) {
            return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);//四舍五入,保留2位小数
        }else{
            return new BigDecimal(0.00);
        }
        //除不尽的情况
    }




    public static BigDecimal avg(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1+v2));
        return b1.divide(new BigDecimal(2),4, BigDecimal.ROUND_HALF_UP);
        //除不尽的情况
    }





}

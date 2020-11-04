package com.example.pedidosdemo.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static boolean isNullOrLowerThanZero(Integer number){
        return number == null || new Integer(0).compareTo(number) > 0;
    }

    public static boolean isNullOrLowerThanZero(BigDecimal number) {
        return number == null || new BigDecimal(0).compareTo(number) > 0;
    }

}

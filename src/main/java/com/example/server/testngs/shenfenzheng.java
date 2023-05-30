package com.example.server.testngs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class shenfenzheng {
}

class StringUtils {
    public static String getIdNo(boolean male){
        //随机生成生日 1~99岁
        long begin = System.currentTimeMillis() - 3153600000000L;//100年内
        long end = System.currentTimeMillis() - 31536000000L; //1年内
        long rtn = begin + (long) (Math.random() * (end - begin));
        Date date = new Date(rtn);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String birth = simpleDateFormat.format(date);
        return getIdNo(birth,male);
    }
    public static String getIdNo(String birth,boolean male){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int value = random.nextInt(Cities.cities.length);
        sb.append(Cities.cities[value]);
        sb.append(birth);
        value = random.nextInt(999) + 1;
        if(male && value % 2 == 0){
            value++;
        }
        if(!male && value % 2 == 1){
            value++;
        }
        if(value >= 100){
            sb.append(value);
        }else if(value >= 10){
            sb.append('0').append(value);
        }else{
            sb.append("00").append(value);
        }
        sb.append(calcTrailingNumber(sb));
        return sb.toString();
    }
    private static final int[] calcC = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    private static final char[] calcR = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

    private static char calcTrailingNumber(StringBuilder sb) {
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(String.valueOf(sb.charAt(i)));
        }
        for (int i = 0; i < n.length; i++) {
            result += calcC[i] * n[i];
        }
        return calcR[result % 11];
    }
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(getIdNo("19790306",true));
        System.out.println(getIdNo("20100112",false));
        System.out.println(getIdNo(true));
        System.out.println(getIdNo(false));
        a = System.currentTimeMillis() - a;
        System.out.println(a);
    }
}
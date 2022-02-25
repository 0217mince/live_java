package com.ke.live.utils;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public class ValidateUtil {
    private static final String NUMERIC_REGEX = "^\\d+(\\.\\d+)?$";

    public static boolean nullOrZeroDouble(Double value){
        return value == null || value == 0;
    }

    /**
     * 判断value是否为null或0
     * @param value
     * @return
     */
    public static boolean notNullDouble(Double value){
        return !(value == null || value == 0);
    }

    /**
     * 判断value是否为 非0整型
     * @param value
     * @return
     */
    public static boolean notNullAndZeroInteger(Integer value){
        return !(value == null || value == 0);
    }

    /**
     * 判断value是否为 null或者0
     * @param value
     * @return
     */
    public static boolean nullOrZeroInteger(Integer value){
        return value == null || value == 0;
    }

    /**
     * 判断value是否为 非0长整型
     * @param value
     * @return
     */
    public static boolean notNullAndZeroLong(Long value){
        return !(value == null || value == 0);
    }
    /**
     * 判断value是否为 null或者0
     * @param value
     * @return
     */
    public static boolean nullOrZeroLong(Long value){
        return value == null || value == 0;
    }

    /**
     * 是否为 非空字符串
     * @param value
     * @return
     */
    public static boolean notBlankString(String value){
        return !(value == null || "".equals(value.trim()));
    }

    /**
     * 是否为 空字符串
     * @param value
     * @return
     */
    public static boolean blankString(String value){
        return value == null || "".equals(value.trim());
    }

    /**
     * 判断是否是空集合
     * @param list
     * @return
     */
    public static boolean blankList(List list){
        return list == null || list.size() == 0;
    }

    /**
     * 判断是否是非空集合
     * @param list
     * @return
     */
    public static boolean notBlankList(List list){
        return !(list == null || list.size() == 0);
    }

    /**
     * 判断Boolean是否为true
     * @param arg
     * @return
     */
    public static boolean isTrue(Boolean arg){
        if(arg==null) {
            return false;
        }
        return arg;
    }

    /**
     * 判断Boolean是否为false
     * @param arg
     * @return
     */
    public static boolean isNotTrue(Boolean arg) {
        return arg == null || !arg;
    }

    /**
     * 判断给定字符串是否为纯数字组合
     * @param value
     * @return
     */
    public static boolean isNum(String value) {
        return !blankString(value) && isStrictNumeric(value.trim());
    }

    /**
     * 判断给定字符串是否是严格的数值 如：“3.1415”->true;“ 3.1415”->false;
     * @param value
     * @return
     */
    public static boolean isStrictNumeric(String value) {
        return !blankString(value) && value.matches(NUMERIC_REGEX);
    }

    /**
     * 判断给定的字符串是否不是数值
     * @param value
     * @return
     */
    public static boolean isNotNum(String value) {
        return blankString(value) || !isStrictNumeric(value.trim());
    }



    public static void main(String[] args) throws ParseException {
        Boolean x = null;
        System.out.println(isTrue(x));

    }

    public static boolean isMobile(String mobile){

        Pattern p = compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}

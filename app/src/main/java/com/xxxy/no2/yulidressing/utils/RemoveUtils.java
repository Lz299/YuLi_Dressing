package com.xxxy.no2.yulidressing.utils;

public class RemoveUtils {
    public static String removeQuotes(String str) {
        // 检查字符串长度是否大于等于2
        if (str.length() >= 2) {
            // 检查字符串是否以双引号开头
            if (str.charAt(0) == '\"') {
                // 检查字符串是否以双引号结尾
                if (str.charAt(str.length() - 1) == '\"') {
                    // 去除字符串的首尾两个字符
                    str = str.substring(1, str.length() - 1);
                }
            }
        }
        // 返回去除双引号后的字符串
        return str;
    }
}
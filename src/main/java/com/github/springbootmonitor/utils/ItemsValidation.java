package com.github.springbootmonitor.utils;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/14 0014 19:06
 */
public class ItemsValidation {

    /**
     * host域名的格式校验 （还未加入校验）
     * 格式待定，目前只要非空，未加入校验
     */
    private final static String host = "(.*?)+";
    /**
     * ipsource格式校验
     * (0-255.0-255.0-255.0-255) 或 ""
     */
    private final static String ipSourceRegex = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))|";
    /**
     * ipv4格式校验
     *  (0-255.0-255.0-255.0-255)
     */
    private final static String ipv4Regex = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
    /**
     * 是否http请求的正则校验
     *  TRUE 或 FALSE
     */
    private final static String httpRegex = "(TRUE|FALSE)";

    /**
     * 描述部分的正则校验(反向)
     * 必须带上前后的转义字符\"和\"，中间内容随意
     */
    private final static String descripRegex = "(\"(.+?)?\")";

    private final static String[] REGEX = {host, ipSourceRegex, ipv4Regex, ipv4Regex, httpRegex, descripRegex};

    public static boolean validate(String str){

        String[] splits = str.split(",");
        if(splits.length< REGEX.length){
            return false;
        }
        for(int i=0;i<REGEX.length;++i){
            if(!splits[i].matches(REGEX[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "\"这里是说明：包含中文字符时，必须得选用支持中文的字符集（如UTF-8）\"";
        System.out.println(ItemsValidation.validate(str));
    }
}
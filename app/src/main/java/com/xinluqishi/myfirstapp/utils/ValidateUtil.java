package com.xinluqishi.myfirstapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shikeyue on 2017/7/30.
 */

public class ValidateUtil {

    /**
     * 判断是否为空字符
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        boolean is = true;
        is = str == null || str.trim().equals("");
        return is;
    }

    /**
     * 判断字符是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        boolean is = str == null || str.trim().equals("");
        return is;
    }

    /**
     * 判断不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){

        return !isBlank(str);
    }

    /**
     * 判断是否为浮点数或者整数
     * @param str
     * @return true Or false
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断是否为正确的邮件格式
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str){
        if(isBlank(str)){
            return false;
        }
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * 判断字符串是否为合法手机号 11位 13 14 15 18开头
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str){
        if(isBlank(str)){
            return false;
        }
        Pattern p = Pattern.compile("^(1[3,4,5,7,8][0-9])\\d{8}$");
        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * 验证身份证号码
     * @param str
     * @return
     */
    public static boolean isIdentifyCardNumber(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern p = Pattern.compile("^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$");
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 判断是不是手机号
     * @param str
     * @return
     */
    public static boolean isNotMobile(String str){

        return !isMobile(str);
    }

    /**
     * 由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间
     *   ^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$
     分开来注释一下：
     ^ 匹配一行的开头位置
     (?![0-9]+$) 预测该位置后面不全是数字
     (?![a-zA-Z]+$) 预测该位置后面不全是字母
     [0-9A-Za-z] {8,16} 由8-16位数字或这字母组成
     $ 匹配行结尾位置
     注：(?!xxxx) 是正则表达式的负向零宽断言一种形式，标识预该位置后不是xxxx字符。
     * @param str
     * @return
     */
    public static boolean isNumAndChar6_20(String str){
        if(isBlank(str)){
            return false;
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        return str.matches(regex);
    }

    /**
     * 判断字符串是数字并且位数在传入参数的范围内
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isNumber(String str, int min, int max) {
        if (isBlank(str)) {
            return false;
        }
        if (min > 0 && max > 0 && min < max) {
            String regex = "^[0-9]{" + String.valueOf(min) + "," + String.valueOf(max) + "}$";
            return str.matches(regex);
        } else {
            return false;
        }
    }

}

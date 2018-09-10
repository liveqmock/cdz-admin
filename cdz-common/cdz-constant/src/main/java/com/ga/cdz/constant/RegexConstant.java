package com.ga.cdz.constant;

/**
 * @author:luqi
 * @description:  正则常量类，变量同一大写，且语义化 final static 修饰
 * @date:2018/9/3_14:25
 */
public class RegexConstant {
    /**电话正则*/
    public final  static String REGEX_PHONE = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
    /**
     * 验证用户名和密码
     */
    public final static String REGEX_USERNAME_PASSWORD = "^[a-zA-Z_0-9]{6,12}$";
    /**
     * 运营商编码验证
     */
    public final static String REGEX_CODE = "^\\d{2}$";
    /**
     * 商户编码验证
     */
    public final static String SHOP_CODE = "^[5]\\d{4}$";
}

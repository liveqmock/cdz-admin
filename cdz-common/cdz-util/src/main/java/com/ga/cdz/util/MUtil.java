package com.ga.cdz.util;

import com.ga.cdz.constant.RegexConstant;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:luqi
 * @description: 常用工具类，该类不依赖其他模块，不写逻辑代码，方法命名不能与业务有关,
 * 引用时候变量名称必须为mUtil，使用@Resource标签引用
 * @date:2018/9/3_11:11
 */
@Component("mUtil")
public class MUtil {


    /**
     * @author:luqi
     * @description:MD5加密
     * @date:2018/9/3_11:11
     * @param:字符串
     * @return:加密字符串
     */
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * @author:luqi
     * @description: 正则表达式验证
     * @date:2018/9/3_11:14
     * @param: 目标字符串
     * @param: 正则表达式
     * @return: 布尔值，是否匹配
     */
    public boolean isMatchRegex(String str, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }


    /**
     * @author:luqi
     * @description: 判断是否为电话
     * @date:2018/9/3_11:22
     * @param: 电话
     * @return: 布尔值，是否匹配
     */
    public boolean isMatchTelphone(String telphone) {
        return isMatchRegex(telphone, RegexConstant.REGEX_PHONE);
    }


    /**
     * @author:luqi
     * @description: 生成32位UUID
     * @date:2018/9/3_11:27
     * @return: 32位UUID 字符串
     */
    public String UUID32() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }


    /**
     * @author:luqi
     * @description: 生成16位UUID
     * @date:2018/9/3_11:27
     * @return: 16位UUID 字符串
     */
    public String UUID16() {
        int first = new Random(10).nextInt(8) + 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

}

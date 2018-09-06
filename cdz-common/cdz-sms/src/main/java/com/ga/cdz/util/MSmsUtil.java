package com.ga.cdz.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class MSmsUtil {
    //产品名称:云通信短信API产品,开发者无需替换
    private final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private final String domain = "dysmsapi.aliyuncs.com";
    @Value("${sms.id}")
    private String accessKeyId;
    @Value("${sms.secret}")
    private String accessKeySecret;
    @Value("${sms.signName}")
    private String signName;
    @Value("${sms.templateCode}")
    private String templateCode;


    private final String SMS_STR = "0123456789";

    /**
     * 生成验证码 4位数字
     */
    public String buildCode() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = SMS_STR.charAt(new Random().nextInt(SMS_STR.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 发送短信验证码
     */
    public boolean sendCode(String phone, String code) {
        boolean rs = false;
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                rs = true;
            } else {
                log.error("短信发送失败====================code={},message={}" +
                                ",requestId={},bizId={}", sendSmsResponse.getCode(),
                        sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            }
        } catch (Exception e) {
            log.error("短信发送异常================================={}", e.getMessage());
        } finally {
            return rs;
        }
    }

    /**
     * 发送短信验证码 并返回详情
     */
    public String sendCodeDetail(String phone, String code) {
        String rs = "";
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            String resultCode = sendSmsResponse.getCode();
            if (sendSmsResponse.getCode() != null && resultCode.equals("OK")) {
                //请求成功
                rs = "";
            } else {
                log.error("短信发送失败====================code={},message={}" +
                                ",requestId={},bizId={}", resultCode,
                        sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
                if (resultCode.equals("isv.BUSINESS_LIMIT_CONTROL")) {
                    rs = "短信验证码发送频繁，请稍后尝试";
                } else {
                    rs = "短信验证码发送失败，请稍后尝试";
                }
            }
        } catch (Exception e) {
            log.error("短信发送异常================================={}", e.getMessage());
            rs = "短信验证码发送失败，请稍后尝试";
        } finally {
            return rs;
        }
    }

}

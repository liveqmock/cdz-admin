package com.ga.cdz.advice;

import com.ga.cdz.annotation.UnDecrypt;
import com.ga.cdz.domain.bean.RSAException;
import com.ga.cdz.util.MBase64Util;
import com.ga.cdz.util.MJsonUtil;
import com.ga.cdz.util.MRSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author:luqi
 * @description:  控制层解密切面，带有@ResquestBody 的参数都会进行拦截
 * @date:2018/9/4_14:33
 */
@Slf4j
@ControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {


    @Value("${privateKey}")
   private String privateKey;

    @Resource
    private MJsonUtil mJsonUtil;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        //判断是否需要解密
        if (!methodParameter.getMethod().isAnnotationPresent(UnDecrypt.class)) {
            try {
                return new DecryptHttpInputMessage(httpInputMessage, privateKey, "utf-8");
            } catch (Exception e) {
                log.error("数据解密失败", e);
            }
        }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    /**
     * @author:luqi
     * @description: DecryptHttpInputMessage 解密流对象
     * @date:2018/9/4_14:52
     */
    class DecryptHttpInputMessage implements HttpInputMessage{
        private HttpHeaders headers;
        private InputStream body;

        public DecryptHttpInputMessage(HttpInputMessage inputMessage, String privateKey, String charset) throws Exception {

            if(StringUtils.isEmpty(privateKey)){
                throw new IllegalArgumentException("privateKey is null");
            }
            //获取请求内容
            this.headers = inputMessage.getHeaders();
            String content = IOUtils.toString(inputMessage.getBody(), charset);
            JSONObject jsonObject=new JSONObject(content);
            content=jsonObject.getString("data");
            //未加密数据不进行解密操作
            String decryptBody;
            if (content.startsWith("{")) {
                throw new RSAException();
            } else {
                StringBuilder json = new StringBuilder();
                content = content.replaceAll(" ", "+");
                if (!StringUtils.isEmpty(content)) {
                    String[] contents = content.split("\\|");
                    for (int k = 0; k < contents.length; k++) {
                        String value = contents[k];
                        value = new String(MRSAUtils.decryptByPrivateKey(MBase64Util.decode(value), privateKey), charset);
                        json.append(value);
                    }
                }
                decryptBody = json.toString();
            }
            this.body = IOUtils.toInputStream(decryptBody, charset);
        }


        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}

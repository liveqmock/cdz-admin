package com.ga.cdz.handle;


import com.ga.cdz.domain.bean.*;
import com.ga.cdz.domain.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), status);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public void handleUnauthorizedException(HttpServletResponse httpResponse) throws IOException {
        httpResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpResponse.setCharacterEncoding("utf-8");
        wrapCorsResponse(httpResponse);
        httpResponse.setStatus(402);
        httpResponse.getWriter().write("没有权限");
    }

    /**
     * 添加cors支持
     *
     * @param response
     */
    private void wrapCorsResponse(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
    }

    /**
     * 进入业务逻辑中出错
     */
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof ValidException) {
            ValidException validException = (ValidException) e;
            //验证参数错误
            return Result.custom().code(validException.getCode())
                    .message(validException.getMessage() + "，" + validException.getDetail());
        } else if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            //业务逻辑异常
            return Result.fail().message(businessException.getDetail());
        } else if(e instanceof RSAException){
            //加密异常
            return Result.custom().code(ResultEnum.RSA_ERROR.getCode())
                    .message(ResultEnum.RSA_ERROR.getMessage());
        } else if (e instanceof UserFreezeException) {
            //用户冻结异常
            return Result.custom().code(ResultEnum.USER_FREEZE.getCode())
                    .message(ResultEnum.USER_FREEZE.getMessage());
        }
        log.error("class:{},detail:{}", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return Result.unkonw().message(e.getMessage());
    }


}

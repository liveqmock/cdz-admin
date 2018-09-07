package com.ga.cdz.config;

import com.ga.cdz.domain.bean.CustomRealm;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * @author:luqi
 * @description: shiro 配置文件
 * @date:2018/9/3_14:28
 */
@Configuration
public class ShiroConfig extends BaseShiroConfig {


     /**
      * @author:luqi
      * @description: 重写基类的生成自定义realm方法
      * @date:2018/9/3_14:29
      * @param:
      * @return: 自定义的realm
      */
    @Override
    public BaseCustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }


     /**
      * @author:luqi
      * @description:  加入不需要过滤的路由
      * @date:2018/9/3_14:29
      * @param:
      * @return: list路由列表
      */
    @Override
    public List<String> filterByAnon() {
        List<String> list = new ArrayList<>();
        //认证相关的接口不需要过滤
        list.add("/account/**");
        //暂时通过
      /*  list.add("/admin/**");
        list.add("/roles/**");*/
        return list;
    }
}

package com.ga.cdz;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 演示 centerMapper 父类，注意这个类不要让 mp 扫描到！！
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    // 这里可以放一些公共的方法
}

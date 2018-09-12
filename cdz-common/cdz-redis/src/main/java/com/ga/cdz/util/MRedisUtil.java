package com.ga.cdz.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author:luqi
 * @description: Redis工具类
 * @date:2018/9/3_13:34
 */
@Slf4j
public class MRedisUtil {

    @Resource
    protected RedisTemplate redisTemplate;


    /**
     * @author:luqi
     * @description: 添加
     * @date:2018/9/3_13:38
     * @param: key
     * @param: value
     * @return:
     */
    public <T> void put(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * @author:luqi
     * @description: 添加—含有超时时间
     * @date:2018/9/3_13:38
     * @param: key
     * @param: value
     * @param: expire (默认为秒) 超时时间
     * @return:
     */
    public <T> void put(String key, T value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * @author:luqi
     * @description: 添加hash
     * @date:2018/9/11_18:18
     * @param:
     * @return:
     */
    public <T> void putHash(String key, String hashKey, T hashValue) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, hashValue);
    }

    /**
     * @author:luqi
     * @description: 添加hash 集合
     * @date:2018/9/11_18:46
     * @param:
     * @return:
     */
    public <K, T> void pushHashAll(String key, Map<K, T> hashValue) {
        redisTemplate.opsForHash().putAll(key, hashValue);
    }




    /**
     * @author:luqi
     * @description: 添加—含有超时时间
     * @date:2018/9/3_13:38
     * @param: key
     * @param: value
     * @param: expire (默认为秒) 超时时间
     * @param: 时间单位
     * @return:
     */
    public <T> void put(String key, T value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }


     /**
      * @author:luqi
      * @description: 删除
      * @date:2018/9/3_13:41
      * @param: key
      * @return: 布尔值，成功与否
      */
    public boolean remove(String key) {
        try {
            redisTemplate.opsForValue().getOperations().delete(key);
            return true;
        } catch (Throwable t) {
            log.error("redis remove " + key + " has error,detail----" + t);
        }
        return false;
    }

    /**
     * @author:luqi
     * @description: 删除hash 根据key
     * @date:2018/9/11_18:47
     * @param: key
     * @param: hashObject
     * @return:
     */
    public <T> boolean removeHash(String key, T... hashValue) {
        try {
            redisTemplate.opsForHash().delete(key, hashValue);
            return true;
        } catch (Throwable t) {
            log.error("redis hash remove " + key + " has error,detail----" + t);
        }
        return false;
    }

    /**
     * @author:luqi
      * @description: 批量删除value通过key的表达式
      * @date:2018/9/3_13:44
      * @param: redis表达式
      * @return: 布尔值，成功与否
      */
    public boolean removeMatch(String match) {
        Set<String> set = redisTemplate.keys(match);
        Iterator<String> it = set.iterator();
        if (it.hasNext()) {
            String keyStr = it.next();
            try {
                redisTemplate.delete(keyStr);
            } catch (Throwable t) {
                log.error("redis remove " + keyStr + " has error,detail----" + t);
                return false;
            }
        }
        return true;
    }

    /**
     * @author:luqi
     * @description: 判断key是否存在
     * @date:2018/9/3_13:45
     * @param: 是否存在key
     * @return: 布尔值，是否存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            log.error("redis has key exec was error " + key + ", detail----" + t);
        }
        return false;
    }


     /**
      * @author:luqi
      * @description: 获取
      * @date:2018/9/3_13:45
      * @param: key
      * @return: 泛型对象（获取失败返回null）
      */
    public <T> T get(String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Throwable t) {
            log.error("redis get key exec was error " + key + ", detail----" + t);
        }
        return null;
    }


}

package com.neuedu.crm.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

/**
 * Redis工具类
 * ：用于缓存数据
 * @author wanghaoyu
 *
 */
@Component
public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    
    TimedCache<String, Object> cache = CacheUtil.newTimedCache(4);
  //实例化创建
//    private RedisTemplate<Serializable, Object> redisTemplate;
//
//    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

//    /**
//     * 批量删除对应的value
//     *
//     * @param keys
//     */
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern
//     */
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0) {
//            redisTemplate.delete(keys);
//        }
//    }
//
//    /**
//     * 删除对应的value
//     *
//     * @param key
//     */
//    public void remove(final String key) {
//        logger.info("要移除的key为：" + key);
//        if (exists(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key
//     * @return
//     */
//    public boolean exists(final String key) {
//        logger.info("要验证是否存在的key为：" + key);
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 读取缓存
//     *
//     * @param key
//     * @return
//     */
//    public Object get(final String key) {
//        Object result = null;
//        ValueOperations<Serializable, Object> operations = redisTemplate
//                .opsForValue();
//        result = operations.get(key);
//        return result;
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate
//                    .opsForValue();
//            operations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            logger.error("系统异常",e);
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public boolean set(final String key, Object value, Long expireTime) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate
//                    .opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            logger.error("系统异常",e);
//        }
//        return result;
//    }
    
    
  /**
  * 批量删除对应的value
  *
  * @param keys
  */
 public void remove(final String... keys) {
     for (String key : keys) {
         remove(key);
     }
 }

// /**
//  * 批量删除key
//  *
//  * @param pattern
//  */
// public void removePattern(final String pattern) {
//     Set<Serializable> keys = redisTemplate.keys(pattern);
//     if (keys.size() > 0) {
//         redisTemplate.delete(keys);
//     }
// }

 /**
  * 删除对应的value
  *
  * @param key
  */
 public void remove(final String key) {
     logger.info("要移除的key为：" + key);
     if (exists(key)) {
//         redisTemplate.delete(key);
    	 cache.remove(key);
     }
 }

 /**
  * 判断缓存中是否有对应的value
  *
  * @param key
  * @return
  */
 public boolean exists(final String key) {
     logger.info("要验证是否存在的key为：" + key);
     
     return cache.containsKey(key);
 }

 /**
  * 读取缓存
  *
  * @param key
  * @return
  */
 public Object get(final String key) {
//     Object result = null;
//     ValueOperations<Serializable, Object> operations = redisTemplate
//             .opsForValue();
//     result = operations.get(key);
     return cache.get(key);
 }

 /**
  * 写入缓存
  *
  * @param key
  * @param value
  * @return
  */
 public boolean set(final String key, Object value) {
     boolean result = false;
     try {
//         ValueOperations<Serializable, Object> operations = redisTemplate
//                 .opsForValue();
//         operations.set(key, value);
    	 cache.put(key, value);
         result = true;
     } catch (Exception e) {
         logger.error("系统异常",e);
     }
     return result;
 }

 /**
  * 写入缓存
  *
  * @param key
  * @param value
  * @return
  */
 public boolean set(final String key, Object value, Long expireTime) {
     boolean result = false;
     try {
//         cache.expire(key, expireTime, TimeUnit.SECONDS);
    	 cache.put(key, value, expireTime);
         result = true;
     } catch (Exception e) {
         logger.error("系统异常",e);
     }
     return result;
 }

}

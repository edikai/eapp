package com.ek.eapp.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: EkRedisUtil
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-07
 * @Version: V2.0
 **/
@Component
public class EkRedisUtil {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final long REDIS_TIME_OUT = 60;

    public boolean containsKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    public <T> boolean set(String key, T value) {
        return this.set(key, value, REDIS_TIME_OUT);
    }

    public <T> boolean set(String key, T value, long timeout) {
        try {
            //任意类型转换成String
            String val = beanToString(value);
            if (val == null || val.length() <= 0) {
                return false;
            }
            stringRedisTemplate.opsForValue().set(key, val, timeout, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public String get(String key){
        return this.get(key, String.class);
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return stringToBean(value, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if (value == null || value.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(value);
        } else if (clazz == String.class) {
            return (T) value;
        } else {
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }

    /**
     * @param T values 任意类型
     * @return String
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }
}

package com.ek.eapp.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CodeUtil
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-04
 * @Version: V2.0
 **/
@Component
public class CodeUtil {

    private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);
    private static ThreadLocal<StringBuilder> threadLocal = new ThreadLocal<StringBuilder>();

    public String generateLong() {
        StringBuilder builder = new StringBuilder(pattern.format(Instant.now().toEpochMilli()));// 取系统当前时间作为订单号前半部分
        builder.append(Math.abs(uuid().hashCode()));// HASH-CODE
        builder.append(atomicInteger.getAndIncrement());// 自增顺序
        threadLocal.set(builder);
        return threadLocal.get().toString();
    }
    public String generateShort() {
        StringBuilder builder = new StringBuilder(ThreadLocalRandom.current().nextInt(0,999));// 随机数
        builder.append(Math.abs(uuid().hashCode()));// HASH-CODE
        builder.append(atomicInteger.getAndIncrement());// 自增顺序
        threadLocal.set(builder);
        return threadLocal.get().toString();
    }

    public String uuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

}

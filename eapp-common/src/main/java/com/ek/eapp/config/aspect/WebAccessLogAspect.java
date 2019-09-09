package com.ek.eapp.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName: WebAccessLogAspect
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-09
 * @Version: V2.0
 **/
@Aspect
@Component
public class WebAccessLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebAccessLogAspect.class);
    @Pointcut("execution(public * com.ek.eapp..controller..*.*(..))")
    public void pointCutMethod() {}
    /**
     * 使用AOP前置通知拦截请求参数信息<br>
     * @param joinPoint
     */
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("name:{},value:{}", name, request.getParameter(name));
        }
        log.info("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        log.info("目标方法名为:" + joinPoint.getSignature().getName());
    }

    /**
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointCutMethod()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }
}

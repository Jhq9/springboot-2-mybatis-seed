package isa.qa.project.core;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *  日志请求Api Logger Aspect
 *
 *  @author May
 *  @date 2018/11/21 14:51
 *  @version 1.0
 */
@Aspect
@Component
public class WebLogAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private ObjectMapper objectMapper;

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * isa.qa.project.api..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        LOGGER.info(
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LOGGER.info("URL : " + request.getRequestURL().toString());
        LOGGER.info("HTTP_METHOD : " + request.getMethod());
        LOGGER.info("IP : " + request.getHeader("x-real-ip"));
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                        + joinPoint
                        .getSignature()
                        .getName());
        LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        LOGGER.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws JsonProcessingException {
        LOGGER.info("RESPONSE :" + objectMapper.writeValueAsString(ret));
        LOGGER.info(
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

}

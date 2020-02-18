package isa.qa.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import isa.qa.project.core.Result;
import isa.qa.project.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

import static isa.qa.project.enums.ResultCodeEnums.*;

/**
 *  Web Mvc Config
 *
 *  @author    May
 *  @date      2018/11/21 10:47
 *  @version   1.0
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    private static final String CHARSET_UTF_8 = "UTF-8";

    private static final String HEADER_CONTENT_TYPE = "Content-type";

    private final ObjectMapper objectMapper;

    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 统一异常处理
     */
    @Override
    public void configureHandlerExceptionResolvers(
            List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e) -> {
            Result result = new Result();
            /*
             * 业务失败的异常，如“账号或密码错误”， 或者存在参数错误
             */
            if (e instanceof IllegalArgumentException || e instanceof ServiceException) {
                result.setCode(BAD_REQUEST).setMessage(e.getMessage());
                LOGGER.info(e.getMessage());
            } else if (e instanceof NoHandlerFoundException) {
                result.setCode(NOT_FOUND)
                        .setMessage("接口 [" + request.getRequestURI() + "] 不存在");
            } else if (e instanceof AccessDeniedException) {
                result.setCode(UNAUTHORIZED).setMessage("没有足够的权限访问该接口");
            } else {
                result.setCode(INTERNAL_SERVER_ERROR)
                        .setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                } else {
                    message = e.getMessage();
                }
                LOGGER.error(message, e);
            }
            responseResult(response, result);
            return new ModelAndView();
        });
    }

    /**
     * 为Response设置Encoding、header等参数
     */
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding(CHARSET_UTF_8);
        response.setHeader(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(200);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(result));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}

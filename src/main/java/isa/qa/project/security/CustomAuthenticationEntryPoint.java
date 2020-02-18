package isa.qa.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 处理未通过Security认证的请求
 *
 * @author May
 * @version 1.0
 * @date 2018/5/6 14:43
 */
@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final String AUTHENTICATION_FAIL_MESSAGE = "请先登录";

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		// This is invoked when user tries to access a secured REST resource without supplying any credentials
		// We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.write(objectMapper.writeValueAsString(ResultGenerator
				.genFailResult(ResultCodeEnums.UNAUTHORIZED, AUTHENTICATION_FAIL_MESSAGE)));
	}
}

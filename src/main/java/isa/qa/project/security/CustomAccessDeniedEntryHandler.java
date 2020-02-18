package isa.qa.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import isa.qa.project.core.ResultGenerator;
import isa.qa.project.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * AccessDeniedException Entry point
 *
 * @author May
 * @version 1.0
 * @date 2020/1/2 12:57
 */
@Component
@AllArgsConstructor
public class CustomAccessDeniedEntryHandler implements AccessDeniedHandler, Serializable {

	private final ObjectMapper objectMapper;

	private static final String ACCESS_DENIED_MESSAGE = "权限不足";

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.write(objectMapper.writeValueAsString(ResultGenerator
				.genFailResult(ResultCodeEnums.UNAUTHORIZED, ACCESS_DENIED_MESSAGE)));
	}
}

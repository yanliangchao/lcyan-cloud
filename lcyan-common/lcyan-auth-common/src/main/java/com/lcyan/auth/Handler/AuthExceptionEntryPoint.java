package com.lcyan.auth.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		Throwable cause = authException.getCause();

		response.setStatus(HttpStatus.OK.value());
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			if (cause instanceof InvalidTokenException) {
		        map.put("code", 401);
		        map.put("msg", "401 Unauthorized");
		        map.put("data", "token 格式非法或已失效");
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        response.getWriter().write(objectMapper.writeValueAsString(map));
			} else {
				map.put("code", 401);
		        map.put("msg", "401 Unauthorized");
		        map.put("data", "token 缺失");
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        response.getWriter().write(objectMapper.writeValueAsString(map));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

package com.lcyan.auth.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NullTokenException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ObjectMapper objectMapper;

    public NullTokenException(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletResponse response = attributes.getResponse();
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("code", 401);
	        map.put("msg", "401 Unauthorized");
	        map.put("data", "token 已失效");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        try {
				response.getWriter().write(objectMapper.writeValueAsString(map));
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} else {
			throw new RuntimeException();
		}
    }
}

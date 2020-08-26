package com.lcyan.auth.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcyan.basic.core.model.ResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Auth权限管理")
@RequestMapping("/auth")
public interface AuthController {
	
	@ApiOperation("退出登录")
    @GetMapping("logout")
    public ResponseBean<Boolean> logout(HttpServletRequest request);
	
	@ApiOperation("获取用户信息")
	@RequestMapping("user")
	public Object user(Authentication authentication);

}

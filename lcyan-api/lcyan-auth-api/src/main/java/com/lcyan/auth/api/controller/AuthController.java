package com.lcyan.auth.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Auth权限管理")
@RequestMapping("/auth")
public interface AuthController {
	
	@ApiOperation("退出登录")
    @GetMapping
    public String logout();

}

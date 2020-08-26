package com.lcyan.user.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcyan.basic.core.model.ResponseBean;
import com.lcyan.user.api.dto.UserDto;

import io.swagger.annotations.Api;


@Api(tags = "User用户管理")
@RequestMapping("/user")
public interface UserController {
	
	
    @GetMapping
    public ResponseBean<List<UserDto>> user();

}

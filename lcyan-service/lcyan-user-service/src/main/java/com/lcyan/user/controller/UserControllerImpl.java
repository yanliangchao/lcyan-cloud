package com.lcyan.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import com.lcyan.basic.core.model.ResponseBean;
import com.lcyan.user.api.controller.UserController;
import com.lcyan.user.api.dto.UserDto;
import com.lcyan.user.api.model.User;
import com.lcyan.user.service.impl.UserServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController{

	private final UserServiceImpl userService;
	
	@Override
	public ResponseBean<List<UserDto>> user() {
		List<User> users = userService.findAll();
		List<UserDto> userDtos = new ArrayList<>(); 
		BeanUtils.copyProperties(users, userDtos);
		return new ResponseBean<List<UserDto>>(userDtos, "查询用户");
	}

}

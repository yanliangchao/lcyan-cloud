package com.lcyan.auth.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lcyan.auth.api.controller.AuthController;

@RestController
public class AuthControllerImpl implements AuthController{

	@Override
	public String logout() {
		
		return "SUCCESS";
	}

}

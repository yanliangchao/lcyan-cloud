package com.lcyan.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RestController;

import com.lcyan.auth.api.controller.AuthController;
import com.lcyan.basic.core.constant.CommonConstant;
import com.lcyan.basic.core.model.ResponseBean;
import com.lcyan.basic.exception.BadRequestException;

@RestController
public class AuthControllerImpl implements AuthController{

	
	@Autowired
    private ConsumerTokenServices consumerTokenServices;
	
	@Override
	public Object user(Authentication authentication) {
		return authentication.getPrincipal();
	}

	@Override
	public ResponseBean<Boolean> logout(HttpServletRequest request) {
		String accessToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(accessToken))
            throw new BadRequestException("accessToken为空.");
        if (accessToken.startsWith(CommonConstant.AUTHORIZATION_BEARER))
            accessToken = accessToken.split(CommonConstant.AUTHORIZATION_BEARER)[1];
        return new ResponseBean<>(consumerTokenServices.revokeToken(accessToken));
	}

}

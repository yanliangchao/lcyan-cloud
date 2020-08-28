package com.lcyan.auth.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcyan.auth.Handler.NullTokenException;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomTokenServices implements ResourceServerTokenServices {
	
	@Autowired
    private ObjectMapper objectMapper;
	
    @Setter
    private TokenStore tokenStore;

    @Setter
    private DefaultAccessTokenConverter defaultAccessTokenConverter;

    @Setter
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        // 从Redis里读取Token
    	OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
    	// 判断Redis里读取的Token是否为空
        if(ObjectUtils.isEmpty(oAuth2Authentication)) {
        	throw new NullTokenException(objectMapper);
        }
        UserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
        defaultAccessTokenConverter.setUserTokenConverter(userTokenConverter);
        // 验证前端传来的Token是否能通过公钥解析
        OAuth2AccessToken readAccessToken = new JwtTokenStore(jwtAccessTokenConverter).readAccessToken(accessToken);
        // 比较解析后的token和redis里的Token
        Map<String, ?> map = jwtAccessTokenConverter.convertAccessToken(readAccessToken, oAuth2Authentication);
        return defaultAccessTokenConverter.extractAuthentication(map);
    }


    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        return tokenStore.readAccessToken(accessToken);
    }
}

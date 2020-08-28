package com.lcyan.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import com.lcyan.auth.utils.SecurityConfigProperties;

/**
 * 授权服务器配置
 *
 * @author tangyi
 * @date 2019-03-14 11:40
 */
@Component
public class CustomAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityConfigProperties properties;
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 将token存储到redis
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
    	//使用redis存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis token存储中的前缀
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }
    
    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //配置token存储
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(jwtTokenEnhancer());
        //开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        //复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        //token有效期，设置12小时
        tokenServices.setAccessTokenValiditySeconds(12 * 60 * 60);
        //refresh_token有效期，设置一周
        tokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return tokenServices;
    }
    /*
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }*/

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
        		new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(properties.getClientId())
                // 测试是跳转到百度，不加这个你配置怎么也不给你跳转,可能是新版本的原因
                .redirectUris("http://www.baidu.com","http://www.aaa.com")
                .secret(properties.getClientSecret())
                .authorizedGrantTypes(
                        properties.getGrantTypePassword(),
                        properties.getAuthorizationCode(),
                        properties.getRefreshToken(),
                        properties.getImplicit())
                .scopes(properties.getScopeRead(), properties.getScopeWrite())
                .accessTokenValiditySeconds(properties.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(properties.getRefreshTokenValiditySeconds());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);*/
        
        endpoints
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
		.authenticationManager(this.authenticationConfiguration.getAuthenticationManager())
        // 将token存储到redis
        .tokenStore(tokenStore())
        .tokenServices(tokenService())
        // token增强
        //.tokenEnhancer(jwtTokenEnhancer())
        .accessTokenConverter(jwtTokenEnhancer())
        // 异常转换
        .exceptionTranslator(webResponseExceptionTranslator());
        
    }
    
    @Bean
    @Lazy
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                if (e instanceof OAuth2Exception) {
                    OAuth2Exception exception = (OAuth2Exception) e;
                    // 转换返回结果
                    return ResponseEntity.status(exception.getHttpErrorCode()).body(new OAuth2Exception(e.getMessage()));
                } else {
                    throw e;
                }
            }
        };
    }
}

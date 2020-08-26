package com.lcyan.auth.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcyan.auth.Handler.AuthExceptionEntryPoint;
import com.lcyan.auth.Handler.CustomAccessDeniedHandler;
import com.lcyan.basic.core.properties.FilterIgnorePropertiesConfig;

/**
 * 资源服务器配置
 *
 * @author ayan2070
 * 
 */
@Configuration
@EnableResourceServer
@Order(6)
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";
    
    /**
     * 开放权限的URL
     */
    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
        //设置token存储
        resources.tokenStore(tokenStore());
        resources.authenticationEntryPoint(authExceptionEntryPoint).accessDeniedHandler(accessDeniedHandler());
        
    }
    
    /**
     * 设置token存储，这一点配置要与授权服务器相一致
     */
    @Bean
    public RedisTokenStore tokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	String[] ignores = new String[filterIgnorePropertiesConfig.getUrls().size()];
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(filterIgnorePropertiesConfig.getUrls().toArray(ignores)).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
    
    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler(objectMapper);
    }
}

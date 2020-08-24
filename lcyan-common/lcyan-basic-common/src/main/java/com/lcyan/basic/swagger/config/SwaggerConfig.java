package com.lcyan.basic.swagger.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Yan Liangchao
 * @version 1.0
 * @date 2020/4/20 14:03
 * @email liangchao.yan-ext@yanfeng.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	

	@Value("${project.version}")
	private String version;

	@Bean
    public Docket createRestApi() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(authorizationParameter());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lcyan"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    /**
     * Authorization 请求头
     *
     * @return Parameter
     */
    private Parameter authorizationParameter() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("Authorization")
                .description("access_token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        return tokenBuilder.build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LCYAN-CLOUD")
                //创建人
                .contact(new Contact("lcyan", "", "liangchao.yan-ext@yanfeng.com"))
                //版本号
                .version(version)
                //描述
                .description("CARROT")
                .build();
    }
}

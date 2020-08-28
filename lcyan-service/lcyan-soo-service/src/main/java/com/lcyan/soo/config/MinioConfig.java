package com.lcyan.soo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    //@ApiModelProperty("endPoint是一个URL，域名，IPv4或者IPv6地址")
    private String endpoint;


    //@ApiModelProperty("accessKey类似于用户ID，用于唯一标识你的账户")
    private String accessKey;

    //@ApiModelProperty("secretKey是你账户的密码")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
        //MinioClient minioClient = new MinioClient(endpoint, port, accessKey, secretKey,secure);
        MinioClient minioClient =
        	    MinioClient.builder()
        	        .endpoint(endpoint)
        	        .credentials(accessKey, secretKey)
        	        .build();
        return minioClient;
    }
    
}

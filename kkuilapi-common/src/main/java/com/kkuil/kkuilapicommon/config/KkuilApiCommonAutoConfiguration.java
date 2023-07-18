package com.kkuil.kkuilapicommon.config;

import com.kkuil.kkuilapicommon.client.KkuilApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kkuilapi.client")
@Data
@ComponentScan
public class KkuilApiCommonAutoConfiguration {
    private String accessKey;

    private String secretKey;

    @Bean
    public KkuilApiClient kkuilApiClient() {
        return new KkuilApiClient(accessKey, secretKey);
    }
}

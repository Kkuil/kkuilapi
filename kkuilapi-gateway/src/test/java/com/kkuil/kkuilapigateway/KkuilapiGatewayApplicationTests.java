package com.kkuil.kkuilapigateway;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class KkuilapiGatewayApplicationTests {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void testJayspy() {
        String username = stringEncryptor.encrypt("root");
        String password = stringEncryptor.encrypt("password");
        log.info("username 密文: " + username);
        log.info("password 密文: " + password);
    }


}

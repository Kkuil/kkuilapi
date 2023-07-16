package com.kkuil.kkuilapi;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class KkuilapiApplicationTests {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void testJayspy() {
        String username = stringEncryptor.encrypt("123456");
        log.info("username 密文: " + username);
    }

}

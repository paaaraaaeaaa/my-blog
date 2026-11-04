package com.wanted.project;

import org.junit.jupiter.api.Test;
import com.wanted.project.global.config.Module07JpaMiniProjectApplication;
import org.springframework.boot.test.context.SpringBootTest;

// 실행 클래스가 하위 패키지(global.config)에 있어 자동으로 찾지 못하므로 직접 지정한다.
@SpringBootTest(classes = Module07JpaMiniProjectApplication.class)
class Module07JpaMiniProjectApplicationTests {

    @Test
    void contextLoads() {
    }

}

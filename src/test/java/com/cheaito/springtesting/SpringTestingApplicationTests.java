package com.cheaito.springtesting;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringTestingApplicationTests {

    @Autowired
    SpringTestingApplication subject;

    @Test
    void contextLoads() {
        assertThat(subject).isNotNull();
    }

    @Test
    void callsMainOnRun() {
        var mockedApplication = mockStatic(SpringApplication.class);
        SpringApplication.run(SpringTestingApplication.class, "Test1", "Test2");
        mockedApplication.verify(() -> SpringTestingApplication.main(new String[]{"Test1", "Test2"}));
        mockedApplication.close();
    }

}

package com.honghap.sample.integration;

import com.honghap.sample.SampleApplication;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

/**
 * Created by gavinkim at 2019-03-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerIntegrationTest {

    private static final String LOCAL_HOST = "http://localhost";

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void sample() {

        ResponseEntity<String> response = template.getForEntity(createUrl("/sample"),String.class);

        assertThat(response.getBody(), Matchers.equalTo("welcome"));
    }

    private String createUrl(String url) {
        return String.format("%s:%s%s",LOCAL_HOST, port, url);
    }
}

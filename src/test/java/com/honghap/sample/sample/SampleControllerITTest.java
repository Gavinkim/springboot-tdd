package com.honghap.sample.sample;

import com.honghap.sample.Application;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by gavinkim at 2019-03-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerITTest {

    private static final String LOCAL_HOST = "http://localhost";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void sample() {

        ResponseEntity<String> response = template.getForEntity(createUrl("/sample"),String.class);

        assertThat(response.getBody(), Matchers.equalTo("welcome"));
    }


    @Test
    public void getSampleDto() throws Exception{

        ResponseEntity<String> response = template.getForEntity(createUrl("/sample/dto"),String.class);

        assertThat(response.getBody(), containsString("welcome"));
    }

    @Test
    public void sampleWithParameter() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createUrl("/sample-with-parameter/gavin"),String.class);
        assertThat(response.getBody(),containsString("welcome, gavin!"));
    }

    private String createUrl(String url) {
        return String.format("%s:%s%s",LOCAL_HOST, port, url);
    }
}

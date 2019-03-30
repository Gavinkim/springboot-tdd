package com.honghap.sample.integration;

import com.honghap.sample.Application;
import com.honghap.sample.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gavinkim at 2019-03-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {

    private static final String LOCAL_HOST = "http://localhost";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void retriveTodos() throws Exception {
        String expected = "[\n" +
                "{\n" +
                "id: 1,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study spring batch\",\n" +
                "targetDate: \"2019-03-31\",\n" +
                "done: false\n" +
                "},\n" +
                "{\n" +
                "id: 2,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study Aparch Spark\",\n" +
                "targetDate: \"2019-04-01\",\n" +
                "done: false\n" +
                "}\n" +
                "]";

        String uri = "/users/gavin/todos";

        ResponseEntity<String> response = template.getForEntity(createUrl(uri),String.class);

        JSONAssert.assertEquals(expected, response.getBody(),false);

    }

     @Test
    public void retrieveTodoFindByNameAndId() throws Exception {
        String expected = "{\n" +
                "id: 4,\n" +
                "user: \"James\",\n" +
                "desc: \"Study Django\",\n" +
                "targetDate: \"2019-04-02\",\n" +
                "done: false\n" +
                "}";
        ResponseEntity<String> response = template.getForEntity(createUrl("/users/james/todos/4"),String.class);
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }


    private String createUrl(String url) {
        return String.format("%s:%s%s",LOCAL_HOST, port, url);
    }
}

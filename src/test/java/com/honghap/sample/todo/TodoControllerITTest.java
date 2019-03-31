package com.honghap.sample.todo;

import com.honghap.sample.Application;
import org.junit.Ignore;
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

/**
 * Created by gavinkim at 2019-03-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerITTest {

    private static final String LOCAL_HOST = "http://localhost";

    @LocalServerPort
    private int port;

    private static LocalDate now = LocalDate.now();

    @Autowired
    private TestRestTemplate template;

    @Test
    public void retrieveTodos() throws Exception {
        String expected = "[\n" +
                "{\n" +
                "id: 1,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study spring batch\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "},\n" +
                "{\n" +
                "id: 2,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study Aparch Spark\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "},\n" +
                "{\n" +
                "id: 3,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study ElasticSearch\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}\n" +
                "]";

        String uri = "/users/gavin/todos";

        ResponseEntity<String> response = template.getForEntity(createUrl(uri),String.class);

        JSONAssert.assertEquals(expected, response.getBody(),false);

    }

     @Test
     @Ignore
    public void retrieveTodoFindByNameAndId() throws Exception {
        String expected = "{\n" +
                "id: 2,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"Study Aparch Spark\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}";
        ResponseEntity<String> response = template.getForEntity(createUrl("/users/gavin/todos/2"),String.class);
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }


    private String createUrl(String url) {
        return String.format("%s:%s%s",LOCAL_HOST, port, url);
    }
}

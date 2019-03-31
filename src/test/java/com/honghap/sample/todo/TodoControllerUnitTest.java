package com.honghap.sample.todo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gavinkim at 2019-03-30
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private static LocalDate now = LocalDate.now();

    @Test
    public void retrieveTodosFindByName() throws Exception {
        List<Todo> mockList = Arrays.asList(
                Todo.builder().id(1).user("gavin").desc("learn spring").isDone(false).targetDate(now).build(),
                Todo.builder().id(2).user("gavin").desc("learn batch").isDone(false).targetDate(now).build()
        );

        when(todoService.retrieveTodos(anyString())).thenReturn(mockList);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/users/gavin/todos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String expected = "[\n" +
                "{\n" +
                "id: 1,\n" +
                "user: \"gavin\",\n" +
                "desc: \"learn spring\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "},\n" +
                "{\n" +
                "id: 2,\n" +
                "user: \"gavin\",\n" +
                "desc: \"learn batch\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}\n" +
                "]";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
    @Test
    public void retriveTodoByNameAndId() throws Exception{
        Todo mockTodo = Todo.builder().id(1).user("gavin").desc("gavin spring").isDone(false).targetDate(now).build();

        when(todoService.retrieveTodo(anyString(),anyInt()) ).thenReturn(mockTodo);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/users/gavin/todos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String expected = "{\n" +
                "id: 1,\n" +
                "user: \"gavin\",\n" +
                "desc: \"gavin spring\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    //fixme: requestbody 값을 넘겨주는 부분에 대한 테스트 필요.
    @Test
    @Ignore
    public void createTodo() throws Exception {
        Todo mockTodo = Todo.builder().user("Gavin").desc("study hive").isDone(false).targetDate(now).build();
        String todo = "{\n" +
                "id: 4,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"study hive\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}";
        when(todoService.addTodo(anyString(),anyString(),isNull(),anyBoolean())).thenReturn(mockTodo);
        mvc.perform(MockMvcRequestBuilders.post("/users/gavin/todos")
                .content(todo)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("location",containsString("/users/gavin/todos/4")));
    }

    @Test
    public void creatTodo_withValidationError() throws Exception{
        Todo mockTodo = Todo.builder().user("Gavin").desc("study").isDone(false).targetDate(LocalDate.of(2019,04,02)).build();
        String todo = "{\n" +
                "id: 4,\n" +
                "user: \"Gavin\",\n" +
                "desc: \"study\",\n" +
                "targetDate: \""+now.toString()+"\",\n" +
                "done: false\n" +
                "}";

        when(todoService.addTodo(anyString(),anyString(),isNull(),anyBoolean()))
                .thenReturn(mockTodo);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/users/gavin/todos")
                .content(todo)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError()).andReturn();
    }


}

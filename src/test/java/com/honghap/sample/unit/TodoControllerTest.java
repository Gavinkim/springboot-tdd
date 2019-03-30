package com.honghap.sample.unit;

import com.honghap.sample.Todo;
import com.honghap.sample.TodoController;
import com.honghap.sample.TodoService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gavinkim at 2019-03-30
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void retrieveTodosFindByName() throws Exception {
        List<Todo> mockList = Arrays.asList(
                Todo.builder().id(1).user("gavin").desc("learn spring").isDone(false).targetDate(LocalDate.of(2019,03,29)).build(),
                Todo.builder().id(2).user("gavin").desc("learn batch").isDone(false).targetDate(LocalDate.of(2019,03,30)).build()
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
                "targetDate: \"2019-03-29\",\n" +
                "done: false\n" +
                "},\n" +
                "{\n" +
                "id: 2,\n" +
                "user: \"gavin\",\n" +
                "desc: \"learn batch\",\n" +
                "targetDate: \"2019-03-30\",\n" +
                "done: false\n" +
                "}\n" +
                "]";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
    @Test
    public void retriveTodoByNameAndId() throws Exception{
        Todo mockTodo = Todo.builder().id(1).user("gavin").desc("gavin spring").isDone(false).targetDate(LocalDate.of(2019,03,29)).build();

        when(todoService.retrieveTodo(anyString(),anyInt()) ).thenReturn(mockTodo);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/users/gavin/todos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String expected = "{\n" +
                "id: 1,\n" +
                "user: \"gavin\",\n" +
                "desc: \"gavin spring\",\n" +
                "targetDate: \"2019-03-29\",\n" +
                "done: false\n" +
                "}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
}

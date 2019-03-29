package com.honghap.sample;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gavinkim at 2019-03-30
 */
@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 4;
    private static LocalDate NOW = LocalDate.now();
    static {
        todos.add(Todo.builder().id(1).desc("Study spring batch").isDone(false).targetDate(NOW.plusDays(1)).user("Gavin").build());
        todos.add(Todo.builder().id(2).desc("Study Aparch Spark").isDone(false).targetDate(NOW.plusDays(2)).user("Gavin").build());
        todos.add(Todo.builder().id(3).desc("Study ElasticSearch").isDone(false).targetDate(NOW.plusDays(1)).user("James").build());
        todos.add(Todo.builder().id(4).desc("Study Django").isDone(false).targetDate(NOW.plusDays(3)).user("James").build());
    }


    public List<Todo> retrieveTodos(String user) {
        return todos.stream().filter(t->t.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());
    }

    public Todo addTodo(Todo todo){
        todo.setId(todoCount++);
        todos.add(todo);
        return todo;
    }


    public Todo retrieveTodo(String user, int id){
        return todos.stream()
                .filter(todo -> id==todo.getId() && user.equalsIgnoreCase(todo.getUser())).findFirst().orElse(Todo.builder().id(id).build());
    }
}

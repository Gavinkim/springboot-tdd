package com.honghap.sample.todo;

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
    private static LocalDate NOW = LocalDate.now();
    private static int todoCount = 3;
    static {
        todos.add(Todo.builder().id(1).desc("Study spring batch").isDone(false).targetDate(NOW).user("Gavin").build());
        todos.add(Todo.builder().id(2).desc("Study Aparch Spark").isDone(false).targetDate(NOW).user("Gavin").build());
        todos.add(Todo.builder().id(3).desc("Study ElasticSearch").isDone(false).targetDate(NOW).user("Gavin").build());
    }


    public List<Todo> retrieveTodos(String user) {
        return todos.stream().filter(t->t.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());
    }

    public Todo addTodo(String name, String desc, LocalDate targetDate, boolean isDone){
        Todo createTodo = Todo.builder()
                .id(todos.size()+1)
                .user(name)
                .targetDate(targetDate)
                .isDone(isDone)
                .desc(desc)
                .build();
        todos.add(createTodo);
        return createTodo;
    }


    public Todo retrieveTodo(String user, int id) throws TodoNotFoundException{
        return todos.stream().filter(todo -> todo.getUser().equalsIgnoreCase(user) && todo.getId()==id ).findFirst().orElse(null);
    }
}

package com.honghap.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by gavinkim at 2019-03-30
 */
@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{name}/todos")
    public List<Todo> retrieveTodos(@PathVariable String name){
        return todoService.retrieveTodos(name);
    }

    @GetMapping("/users/{name}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String name, @PathVariable int id) {
        return todoService.retrieveTodo(name, id);
    }

    @PostMapping("/users/{name}/todos")
    ResponseEntity<?> add(@RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(todo );
        if (ObjectUtils.isEmpty(createdTodo)) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}

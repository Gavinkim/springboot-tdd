package com.honghap.sample.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.hateoas.Resource;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public Resource<Todo> retrieveTodo(@PathVariable String name, @PathVariable int id) {
        Todo todo = todoService.retrieveTodo(name,id);
        if(ObjectUtils.isEmpty(todo)) {
            throw new TodoNotFoundException("Todo Not Found");
        }
        Resource<Todo> todoResource = new Resource<>(todo);
        ControllerLinkBuilder linkTodo = linkTo(methodOn(this.getClass()).retrieveTodos(name));
        todoResource.add(linkTodo.withRel("parent"));

        return todoResource;
    }

    @PostMapping("/users/{name}/todos")
    ResponseEntity<?> add(@PathVariable String name,@RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(name,todo.getDesc(), todo.getTargetDate(), todo.isDone());
        if (ObjectUtils.isEmpty(createdTodo)) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/dummy-service")
    public Todo errorService() {
        throw new RuntimeException("Some exception occured");
    }

}

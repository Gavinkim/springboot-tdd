package com.honghap.sample.todo;

/**
 * Created by gavinkim at 2019-03-31
 */
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String msg) {
        super(msg);
    }
}

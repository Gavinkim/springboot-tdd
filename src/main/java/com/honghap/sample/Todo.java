package com.honghap.sample;

import lombok.*;

import java.time.LocalDate;

/**
 * Created by gavinkim at 2019-03-30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Todo {

    private int id;
    private String user;
    private String desc;
    private LocalDate targetDate;
    private boolean isDone;

    @Builder
    public Todo(int id, String user, String desc, LocalDate targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }
}

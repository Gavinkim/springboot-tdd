package com.honghap.sample.todo;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    private String user;
    @Size(min = 9, message = "Enter atleast 10 Characters.")
    private String desc;
    @Future
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

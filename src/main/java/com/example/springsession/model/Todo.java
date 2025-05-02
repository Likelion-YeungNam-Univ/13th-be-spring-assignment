package com.example.springsession.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private int id;
    private String title;
    private boolean completed;

}

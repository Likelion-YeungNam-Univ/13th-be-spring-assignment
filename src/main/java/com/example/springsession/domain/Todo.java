package com.example.springsession.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}

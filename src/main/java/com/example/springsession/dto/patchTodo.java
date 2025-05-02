package com.example.springsession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//title, description, completed를 위한 부분 수정 dto
public class patchTodo {
    private String title;
    private String description;
    private Boolean completed;
}

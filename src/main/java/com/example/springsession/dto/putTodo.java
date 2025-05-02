package com.example.springsession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class putTodo {
    private String title;
    private String description;
    private Boolean completed;

    public boolean checkNull() {
        boolean answer = true;
        if(title != null && description != null && completed != null) answer = false;
        return answer;
    }
}

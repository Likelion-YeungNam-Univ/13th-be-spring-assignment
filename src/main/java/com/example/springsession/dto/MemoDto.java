package com.example.springsession.dto;

import com.example.springsession.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemoDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public Memo toEntity() {
        return new Memo(id, title, content, author);
    }

    public boolean checkNull(){
        return !(title != null && content != null && author != null);
    }
}

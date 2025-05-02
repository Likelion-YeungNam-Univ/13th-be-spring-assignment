package com.example.springsession.entity;

import com.example.springsession.dto.MemoDto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Memo {
    private Long id;
    private String title;
    private String content;
    private String author;

    public void put(MemoDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = dto.getAuthor();
    }
    // 차이를 위해 메소드 구분
    public void patch(MemoDto dto){
        if(dto.getTitle() != null) this.title = dto.getTitle();
        if(dto.getContent() != null) this.content = dto.getContent();
        if(dto.getAuthor() != null) this.author = dto.getAuthor();
    }

    public MemoDto toDto() {
        return new MemoDto(id, title, content, author);
    }
}

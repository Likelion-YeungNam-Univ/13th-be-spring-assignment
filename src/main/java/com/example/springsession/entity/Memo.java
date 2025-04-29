package com.example.springsession.entity;

import com.example.springsession.dto.MemoDto;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;

    public void put(MemoDto dto){
        if(this.id != dto.getId()) throw new IllegalArgumentException("잘못된 id 매칭");
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = dto.getAuthor();
    }
    // 차이를 위해 메소드 구분
    public void patch(MemoDto dto){
        if(this.id != dto.getId()) throw new IllegalArgumentException("잘못된 id 매칭");
        if(dto.getTitle() != null) this.title = dto.getTitle();
        if(dto.getContent() != null) this.content = dto.getContent();
        if(dto.getAuthor() != null) this.author = dto.getAuthor();
    }

    public MemoDto toDto() {
        return new MemoDto(id, title, content, author);
    }
}

package com.example.springsession.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long id;                //책 고유번호(자동 할당)
    private String title;           //책 제목
    private String author;          //책 저자
    private String categoryCode;    //책 분류 번호(600, 800 ..)
}

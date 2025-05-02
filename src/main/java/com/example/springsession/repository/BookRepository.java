package com.example.springsession.repository;

import com.example.springsession.entity.Book;

import java.util.List;

public interface BookRepository {
    //GET 관련 검색 메소드
    List<Book> findAll();
    Book findById(Long id);

    //POST 관련 메소드
    Long save(Book book);

    //PUT, PATCH 관련 메소드
    Long fullUpdate(Long id, Book book);
    Long partialUpdate(Long id, Book book);

    //DELETE 관련 메소드
    void deleteById(Long id);

    //GET 관련 검색 메소드(확장.ver)
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategoryCode(String categoryCode);

    //ID check
    boolean existById(Long id);
}

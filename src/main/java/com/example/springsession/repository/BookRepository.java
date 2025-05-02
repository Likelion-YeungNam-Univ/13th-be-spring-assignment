package com.example.springsession.repository;

import com.example.springsession.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findById(Long id);
    Long save(Book book);
    Long fullUpdate(Long id, Book book);
    Long partialUpdate(Long id, Book book);
    void deleteById(Long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategoryCode(String categoryCode);
    boolean existById(Long id);
}

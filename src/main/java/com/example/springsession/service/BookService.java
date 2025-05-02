package com.example.springsession.service;

import com.example.springsession.entity.Book;
import com.example.springsession.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //전체 검색
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //단일 검색(id)
    public Book getBookById(Long id) {
        if(!existById(id)) return null;
        return bookRepository.findById(id);
    }

    //신규 책 등록
    public Long createBook(Book book){
        return bookRepository.save(book);
    }

    //책 정보 업데이트(PUT)
    public Long updateBook(Long id, Book book){
        if(!existById(id)) return createBook(book);
        return bookRepository.fullUpdate(id, book);
    }

    //책 정보 업데이트(PATCH)
    public Long patchBook(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id);
        if(book == null) return null;

        if(updates.containsKey("title") && updates.get("title") != null)
            book.setTitle((String) updates.get("title"));
        if(updates.containsKey("author") && updates.get("author") != null)
            book.setAuthor((String) updates.get("author"));
        if(updates.containsKey("categoryCode") && updates.get("categoryCode") != null)
            book.setCategoryCode((String) updates.get("categoryCode"));

        return bookRepository.partialUpdate(id, book);
    }

    //책 삭제
    public boolean deleteBook(Long id) {
        if(!existById(id)) return false;

        bookRepository.deleteById(id);
        return true;
    }

    //단일 검색(title)
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    //단일 검색(author)
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    //단일 검색(categoryCode)
    public List<Book> searchByCategoryCode(String categorycode) {
        return bookRepository.findByCategoryCode(categorycode);
    }

    //id(책) 존재 여부 확인
    public boolean existById(Long id){
        return bookRepository.existById(id);
    }
}

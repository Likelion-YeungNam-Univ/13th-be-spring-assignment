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

    public Book getBookById(Long id) {
        if(!existById(id)) return null;
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Long createBook(Book book){
        return bookRepository.save(book);
    }

    public Long updateBook(Long id, Book book){
        if(!existById(id)) return createBook(book);
        return bookRepository.fullUpdate(id, book);
    }

    public Long patchBook(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id);
        if(book == null) return null;

        if(updates.containsKey("title") && updates.get("title") != null)
            book.setTitle((String) updates.get("title"));
        if(updates.containsKey("author") && updates.get("author") != null)
            book.setAuthor((String) updates.get("author"));
        if(updates.containsKey("categoryCode") && updates.get("categoryCode") != null)
            book.setCategoryCode((String) updates.get("categoryCode"));

        return bookRepository.fullUpdate(id, book);
    }

    public boolean deleteBook(Long id) {
        if(!existById(id)) return false;

        bookRepository.deleteById(id);
        return true;
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> searchByCategoryCode(String categorycode) {
        return bookRepository.findByCategoryCode(categorycode);
    }

    public boolean existById(Long id){
        return bookRepository.existById(id);
    }
}

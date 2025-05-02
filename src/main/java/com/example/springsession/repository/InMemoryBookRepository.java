package com.example.springsession.repository;

import com.example.springsession.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryBookRepository implements BookRepository{
    private Map<Long, Book> bookDB = new HashMap<>();
    private Map<String, List<Book>> booksByTitle = new HashMap<>();
    private Map<String, List<Book>> booksByAuthor = new HashMap<>();
    private Map<String, List<Book>> booksByCategoryCode = new HashMap<>();
    private Long bookCount = 0L;

    @Override
    public Book findById(Long id) {
        return bookDB.get(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDB.values().stream().toList();
    }

    @Override
    public Long save(Book book) {
        book.setId(++bookCount);
        bookDB.put(book.getId(), book);
        booksByTitle.computeIfAbsent(book.getTitle(), title -> new ArrayList<>()).add(book);
        booksByAuthor.computeIfAbsent(book.getAuthor(), author -> new ArrayList<>()).add(book);
        booksByCategoryCode.computeIfAbsent(book.getCategoryCode(), bookCategory -> new ArrayList<>()).add(book);
        return book.getId();
    }

    @Override
    public Long fullUpdate(Long id, Book book) {
        Book preBook = bookDB.get(id);
        if(preBook == null) return null;

        List<Book> titleList = booksByTitle.get((preBook.getTitle()));
        if(titleList != null) titleList.remove(preBook);

        List<Book> authorList = booksByAuthor.get((preBook.getAuthor()));
        if(authorList != null) authorList.remove(preBook);

        List<Book> categoryList = booksByCategoryCode.get((preBook.getCategoryCode()));
        if(categoryList != null) categoryList.remove(preBook);
        bookDB.remove(id);

        bookDB.put(id, book);
        booksByTitle.computeIfAbsent(book.getTitle(), title -> new ArrayList<>()).add(book);
        booksByAuthor.computeIfAbsent(book.getAuthor(), author -> new ArrayList<>()).add(book);
        booksByCategoryCode.computeIfAbsent(book.getCategoryCode(), booksByCategoryCode -> new ArrayList<>()).add(book);
        book.setId(id);
        return id;
    }

    @Override
    public Long partialUpdate(Long id, Book patchData){
        Book preBook = bookDB.get(id);
        if (preBook == null) return null;

        booksByTitle.get(preBook.getTitle()).remove(preBook);
        booksByAuthor.get(preBook.getAuthor()).remove(preBook);
        booksByCategoryCode.get(preBook.getCategoryCode()).remove(preBook);

        if(patchData.getTitle() != null) preBook.setTitle(patchData.getTitle());
        if(patchData.getAuthor() != null) preBook.setAuthor(patchData.getAuthor());
        if(patchData.getCategoryCode() != null) preBook.setCategoryCode(patchData.getCategoryCode());

        booksByTitle.computeIfAbsent(preBook.getTitle(), title -> new ArrayList<>()).add(preBook);
        booksByAuthor.computeIfAbsent(preBook.getAuthor(), author -> new ArrayList<>()).add(preBook);
        booksByCategoryCode.computeIfAbsent(preBook.getCategoryCode(), booksByCategoryCode -> new ArrayList<>()).add(preBook);

        return id;
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookDB.get(id);
        if(book == null) return;

        List<Book> titleList = booksByTitle.get(book.getTitle());
        if(titleList != null){
            titleList.remove(book);
            if(titleList.isEmpty())
                booksByTitle.remove(book.getTitle());
        }

        List<Book> authorList = booksByAuthor.get(book.getAuthor());
        if(authorList != null){
            authorList.remove(book);
            if(authorList.isEmpty())
                booksByAuthor.remove(book.getAuthor());
        }

        List<Book> categoryCodeList = booksByCategoryCode.get(book.getCategoryCode());
        if(categoryCodeList != null){
            categoryCodeList.remove(book);
            if( categoryCodeList.isEmpty())
                booksByCategoryCode.remove(book.getCategoryCode());
        }
        bookDB.remove(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return booksByTitle.getOrDefault(title, List.of());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return booksByAuthor.getOrDefault(author, List.of());
    }

    @Override
    public List<Book> findByCategoryCode(String categoryCode) {
        return booksByCategoryCode.getOrDefault(categoryCode, List.of());
    }

    @Override
    public boolean existById(Long id) {
        return bookDB.containsKey(id);
    }
}

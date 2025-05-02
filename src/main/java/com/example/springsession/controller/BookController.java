package com.example.springsession.controller;

import com.example.springsession.entity.Book;
import com.example.springsession.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    //전체 검색 "/books"
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks()); //성공시 200
    }

    //단일 검색 "/books/bookId"
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable Long bookId){
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 책을 찾을 수 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    //신규 책 등록 "/books"
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book){
        Long savedBookId = bookService.createBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookId + "번 책 추가");
    }

    //책 정보 업데이트(PUT) "/books/bookId"
    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody Book book){
        Long updateBookId = bookService.updateBook(bookId, book);

        if(updateBookId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 id(" + bookId + ")의 책은 존재하지 않습니다.");
        }
        return ResponseEntity.ok(updateBookId + "책 정보를 업데이트 하였습니다.");
    }

    //책 정보 업데이트(PATCH) "/books/bookId"
    @PatchMapping("/{bookId}")
    public ResponseEntity<?> patchBook(@PathVariable Long bookId, @RequestBody Map<String, Object> updates){
        Long patchedBookId = bookService.patchBook(bookId, updates);

        if(patchedBookId == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 책을 찾을 수 없습니다.");

        return ResponseEntity.ok("책(ID: " + patchedBookId + ")이 성공적으로 수정되었습니다.");
    }

    //책 삭제 "/books/bookId"
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        boolean isDeleted = bookService.deleteBook(bookId);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("삭제에 성공하였습니다.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제에 실패하였습니다.");
    }

    //단일 검색(title, author, categoryCode)
    // "/books/search?title="title""
    // "/books/search?author="author""
    // "/books/search?categoryCode="categoryCode""
    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) String author,
                                         @RequestParam(required = false) String categoryCode){
                                        //required = false는 파라미터의 필요 여부를 조절 가능하게 함
        if (title != null) return ResponseEntity.ok(bookService.searchByTitle(title));
        else if (author != null) return ResponseEntity.ok(bookService.searchByAuthor(author));
        else if (categoryCode != null) return ResponseEntity.ok(bookService.searchByCategoryCode(categoryCode));
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("검색 조건이 필요합니다.");
    }
}

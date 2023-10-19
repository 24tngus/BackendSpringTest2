package com.springboot.MySpringBoot.controller;

import com.springboot.MySpringBoot.dto.BookReqDTO;
import com.springboot.MySpringBoot.dto.BookResDTO;
import com.springboot.MySpringBoot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController2 {
    private final BookService bookService;

    // 등록
    @PostMapping
    public BookResDTO saveBook(@RequestBody BookReqDTO bookReqDTO) {
        return bookService.saveBook(bookReqDTO);
    }

    // 조회
    @GetMapping("/{id}")
    public BookResDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    // 전체 목록 조회
    @GetMapping
    public List<BookResDTO> getBooks() {
        return bookService.getBooks();
    }

    // 수정
    @PutMapping("/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO) {
        return bookService.updateBook(id, bookReqDTO);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + " Book이 삭제처리 되었습니다.");
    }
}

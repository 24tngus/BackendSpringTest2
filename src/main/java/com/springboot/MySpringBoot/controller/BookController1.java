package com.springboot.MySpringBoot.controller;

import com.springboot.MySpringBoot.entity.Book;
import com.springboot.MySpringBoot.exception.BusinessException;
import com.springboot.MySpringBoot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController1 {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book create(@RequestBody Book book) {

        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getBooks() {

        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        Optional<Book> optional = bookRepository.findById(id);

        Book book = optional.orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return book;
    }

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book isbn이 존재하지 않습니다", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book exist = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book id가 존재하지 않습니다", HttpStatus.NOT_FOUND));
        if (book.getTitle() != null)
            exist.setTitle(book.getTitle());
        if (book.getAuthor() != null)
            exist.setAuthor(book.getAuthor());
        if (book.getGenre() != null)
            exist.setGenre(book.getGenre());
        return bookRepository.save(exist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
       Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
        return ResponseEntity.ok(id + " Book이 목록에서 삭제 되었습니다");
    }
}

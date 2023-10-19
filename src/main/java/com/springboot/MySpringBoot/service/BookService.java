package com.springboot.MySpringBoot.service;

import com.springboot.MySpringBoot.dto.BookReqDTO;
import com.springboot.MySpringBoot.dto.BookResDTO;
import com.springboot.MySpringBoot.entity.Book;
import com.springboot.MySpringBoot.exception.BusinessException;
import com.springboot.MySpringBoot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    // 등록
    public BookResDTO saveBook(BookReqDTO bookReqDTO) {
        Book book = modelMapper.map(bookReqDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResDTO.class);
    }

    // 조회
    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id) {
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public BookResDTO getBookByIsbn(String isbn) {
        Book bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn + "Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    // 전체 목록 조회
    @Transactional(readOnly = true)
    public List<BookResDTO> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDTO> bookResDTOList = bookList.stream()
                .map(book -> modelMapper.map(book, BookResDTO.class))
                .collect(toList());

        return bookResDTOList;
    }

    // 수정
    public BookResDTO updateBook(Long id, BookReqDTO bookReqDto) {
        Book exist = bookRepository.findById(id).orElseThrow(() ->
                new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));

        if (bookReqDto.getTitle() != null)
            exist.setTitle(bookReqDto.getTitle());
        if (bookReqDto.getAuthor() != null)
            exist.setAuthor(bookReqDto.getAuthor());
        if (bookReqDto.getGenre() != null)
            exist.setGenre(bookReqDto.getGenre());
        bookRepository.save(exist);
        return modelMapper.map(exist, BookResDTO.class);
    }
    // 삭제
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id) //Optional<Customer>
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }
}

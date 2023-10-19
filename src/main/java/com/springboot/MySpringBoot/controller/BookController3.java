package com.springboot.MySpringBoot.controller;

import com.springboot.MySpringBoot.dto.BookReqDTO;
import com.springboot.MySpringBoot.dto.BookResDTO;
import com.springboot.MySpringBoot.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController3 {
    private final BookService bookService;

    // 목록 출력
    @GetMapping("/index")
    public ModelAndView index() {
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        return new ModelAndView("index", "book", bookResDTOList);
    }

    // 등록 폼 호출
    @GetMapping("/add")
    public String showAddForm(BookReqDTO book) {
        return "add-book";
    }

    // 등록
    @PostMapping("/addbook")
    public String addBook(@Valid BookReqDTO book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.saveBook(book);
        return "redirect:/book/index";
    }

    // 수정 폼 호출
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResDTO);
        return "update-book";
    }

    // 수정
    @PostMapping("/update/{id}")
    public String updateBook (@Valid @ModelAttribute("book") BookReqDTO book, BindingResult result, Model model, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "update-book";
        }
        bookService.updateBook(id, book);
        return "redirect:/book/index";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/book/index";
    }
}

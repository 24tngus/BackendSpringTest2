package com.springboot.MySpringBoot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BookReqDTO {
    private Long id;
    @NotEmpty(message = "title은 필수 입력 항목입니다")
    private String title;
    @NotEmpty(message = "author은 필수 입력 항목입니다")
    private String author;
    @NotBlank(message = "isbn은 필수 입력 항목입니다")
    private String isbn;
    private String genre;
}

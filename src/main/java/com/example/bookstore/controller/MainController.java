package com.example.bookstore.controller;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Views;
import com.example.bookstore.repo.AuthorRepo;
import com.example.bookstore.repo.BookRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/task")
public class MainController {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/second")
    @JsonView(Views.authorsFields.class)
    public List<Author> showOldAuthorsSortByBorn() {
        return authorRepo.findOldAuthorsSortByBorn(LocalDate.now().minusYears(55));
    }

    @GetMapping("/third")
    @JsonView(Views.booksFields.class)
    public List<Book> showBooksWhoseAuthorHasMoreThanOne() {
        return authorRepo.findBooksWhoseAuthorHasMoreThanOne();
    }

    @GetMapping("/fourth")
    @JsonView(Views.authorsFields.class)
    public List<Author> showAuthorWhichHasMostBooks() {
        return authorRepo.findAuthorWhichHasMostBooks();
    }

    @GetMapping("/fifth")
    public List<Book> showNumberOfBooksByGenre() {
        return bookRepo.calculateNumberOfBooksByGenre();
    }
}

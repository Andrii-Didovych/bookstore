package com.example.bookstore.controller;

import com.example.bookstore.domain.BookAuthor;
import com.example.bookstore.domain.Views;
import com.example.bookstore.service.AuthorBookService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookAuthorController {

    @Autowired
    private AuthorBookService service;

    @GetMapping("/authors-books")
    @JsonView(Views.booksAuthorsIds.class)
    public List<BookAuthor> findAll() {
        return service.findAll();
    }

    @GetMapping("/author/{authorId}/book/{bookId}")
    @JsonView(Views.booksAuthorsIds.class)
    public BookAuthor findOne(@PathVariable Long authorId, @PathVariable Long bookId) {
        return service.findOne(authorId, bookId);
    }

    @PostMapping("/authors-books")
    public BookAuthor create(@RequestBody BookAuthor bookAuthor) {
        return service.save(bookAuthor);
    }

    @PutMapping("/author/{authorId}/book/{bookId}")
    public BookAuthor update(@PathVariable Long authorId, @PathVariable Long bookId, @RequestBody BookAuthor bookAuthor) {
        return service.update(authorId, bookId, bookAuthor);
    }

    @DeleteMapping("/author/{authorId}/book/{bookId}")
    public void delete(@PathVariable Long authorId, @PathVariable Long bookId) {
        service.delete( authorId, bookId);
    }
}

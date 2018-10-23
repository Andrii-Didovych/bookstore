package com.example.bookstore.controller;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Views;
import com.example.bookstore.service.BookService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    @JsonView(Views.booksFields.class)
    public List<Book> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.booksFields.class)
    public Book findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return service.save(book);
    }

    @PutMapping("{id}")
    @JsonView(Views.booksFields.class)
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package com.example.bookstore.controller;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Views;
import com.example.bookstore.service.AuthorService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    @JsonView(Views.authorsFields.class)
    public List<Author> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.authorsFields.class)
    public Author findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public Author create(@RequestBody Author authors) {
        return service.save(authors);
    }

    @PutMapping("{id}")
    public Author update(@PathVariable Long id, @RequestBody Author authors) {
        return service.update(id, authors);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

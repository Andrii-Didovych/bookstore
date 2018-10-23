package com.example.bookstore.service;

import com.example.bookstore.domain.Author;
import com.example.bookstore.exeption.BadRequestException;
import com.example.bookstore.exeption.NotFoundException;
import com.example.bookstore.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo repo;

    public List<Author> findAll() {
        return repo.findAll();
    }

    public Author findOne(Long id) {
        return repo.findById(id).orElseThrow(NotFoundException::new);
    }

    public Author save(Author author) {
        Author newAuthor = new Author();
        return saveAuthor(newAuthor, author);
    }

    public Author update(Long id, Author author) {
        Author authorFromDb = repo.findById(id).orElseThrow(NotFoundException::new);
        authorFromDb.setId(id);
        return saveAuthor(authorFromDb, author);
    }

    public void delete(Long id) {
        repo.findById(id).orElseThrow(NotFoundException::new);
        repo.deleteById(id);
    }

    private Author saveAuthor(Author newAuthor, Author author) {
        if (authorAlreadyExist(author)) throw new BadRequestException();
        newAuthor.setName(author.getName());
        newAuthor.setBorn(author.getBorn());
        newAuthor.setGender(author.getGender());
        return repo.save(newAuthor);
    }

    private boolean authorAlreadyExist(Author author) {
        return repo.findAuthorByMainFields(author.getName(), author.getBorn())!=null;
    }
}

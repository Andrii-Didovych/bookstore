package com.example.bookstore.service;

import com.example.bookstore.domain.BookAuthor;
import com.example.bookstore.exeption.BadRequestException;
import com.example.bookstore.exeption.NotFoundException;
import com.example.bookstore.repo.AuthorRepo;
import com.example.bookstore.repo.BookAuthorRepo;
import com.example.bookstore.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorBookService {

    @Autowired
    private BookAuthorRepo repo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    public List<BookAuthor> findAll() {
        return repo.findAll();
    }

    public BookAuthor findOne(Long authorId, Long bookId) {
        BookAuthor bookAuthor = repo.getOneByAuthorAndBookIds(authorId, bookId);
        if (bookAuthor == null) throw new NotFoundException();
        return bookAuthor;
    }

    public BookAuthor save(BookAuthor bookAuthor) {
        if (!authorAndBookExist(bookAuthor)) throw new NotFoundException();
        if (authorAlreadyHasBook(bookAuthor)) throw new BadRequestException();
        BookAuthor newBookAuthor = new BookAuthor();
        newBookAuthor.setBookId(bookAuthor.getBookId());
        newBookAuthor.setAuthorId(bookAuthor.getAuthorId());
        return repo.save(newBookAuthor);
    }

    public BookAuthor update(Long authorId, Long bookId, BookAuthor bookAuthor) {
        if (!authorAndBookExist(bookAuthor)) throw new NotFoundException();
        if (authorAlreadyHasBook(bookAuthor)) throw new BadRequestException();
        BookAuthor bookAuthorFromDb = repo.getOneByAuthorAndBookIds(authorId, bookId);
        if (bookAuthorFromDb == null) throw new NotFoundException();
        bookAuthorFromDb.setBookId(bookAuthor.getBookId());
        bookAuthorFromDb.setAuthorId(bookAuthor.getAuthorId());
        return repo.save(bookAuthorFromDb);
    }

    public void delete(Long authorId, Long bookId) {
        BookAuthor bookAuthorFromDb = repo.getOneByAuthorAndBookIds(authorId, bookId);
        if (bookAuthorFromDb == null) throw new NotFoundException();
        repo.delete(bookAuthorFromDb);
    }

    private boolean authorAndBookExist(BookAuthor bookAuthor) {
        return bookRepo.findById(bookAuthor.getBookId()).orElse(null) != null &&
                authorRepo.findById(bookAuthor.getAuthorId()).orElse(null) != null;
    }

    private boolean authorAlreadyHasBook(BookAuthor bookAuthor) {
        return repo.getOneByAuthorAndBookIds(bookAuthor.getAuthorId(), bookAuthor.getBookId())!=null;
    }
}

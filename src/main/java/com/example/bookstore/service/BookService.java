package com.example.bookstore.service;

import com.example.bookstore.domain.Book;
import com.example.bookstore.exeption.BadRequestException;
import com.example.bookstore.exeption.NotFoundException;
import com.example.bookstore.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;


    public List<Book> findAll() {
        return repo.findAll();
    }

    public Book findOne(Long id) {
        return repo.findById(id).orElseThrow(NotFoundException::new);
    }


    public Book save(Book book) {
        Book newBook = new Book();
        return saveBook(newBook, book);
    }

    public Book update(Long id, Book book) {
        Book bookFromDb = repo.findById(id).orElseThrow(NotFoundException::new);
        bookFromDb.setId(id);
        return saveBook(bookFromDb, book);
    }

    public void delete(Long id) {
        repo.findById(id).orElseThrow(NotFoundException::new);
        repo.deleteById(id);
    }

    private Book saveBook(Book newBook, Book book) {
        if (bookAlreadyExist(book)) throw new BadRequestException();
        newBook.setName(book.getName());
        newBook.setGenre(book.getGenre());
        newBook.setPublished(book.getPublished());
        newBook.setRating(book.getRating());
        return repo.save(newBook);
    }

    private boolean bookAlreadyExist(Book book) {
        return repo.findBookByMainFields(book.getName(), book.getPublished())!=null;
    }
}

package com.example.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.booksAuthorsIds.class)
    private Long id;

    @Column(name = "author_id")
    @JsonView(Views.booksAuthorsIds.class)
    private Long authorId;

    @Column(name = "book_id")
    @JsonView(Views.booksAuthorsIds.class)
    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}

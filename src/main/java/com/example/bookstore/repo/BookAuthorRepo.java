package com.example.bookstore.repo;

import com.example.bookstore.domain.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookAuthorRepo extends JpaRepository<BookAuthor, Long> {

    @Query("select ba from BookAuthor ba where ba.authorId =?1 and ba.bookId=?2")
    BookAuthor getOneByAuthorAndBookIds(Long authorId, Long bookId);
}

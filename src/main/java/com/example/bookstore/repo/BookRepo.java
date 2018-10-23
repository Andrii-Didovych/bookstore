package com.example.bookstore.repo;

import com.example.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface BookRepo extends JpaRepository<Book, Long> {

    @Query("SELECT a.genre, count (a.genre) FROM Book a group by a.genre")
    List<Book> calculateNumberOfBooksByGenre();

    @Query("select b from Book b where b.name=?1 and b.published=?2")
    Book findBookByMainFields(String name, LocalDate published);

}

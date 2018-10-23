package com.example.bookstore.repo;

import com.example.bookstore.domain.Author;
import com.example.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.born<?1 order by a.born")
    List<Author> findOldAuthorsSortByBorn(LocalDate particularDate);

    @Query("select a.books from Author a where size(a.books)>1")
    List<Book> findBooksWhoseAuthorHasMoreThanOne();

    @Query("select a from Author a where size(a.books) = (select max (a.books.size) from Author a)")
    List<Author> findAuthorWhichHasMostBooks();

    @Query("select a from Author a where a.name=?1 and a.born=?2")
    Author findAuthorByMainFields(String name, LocalDate born);
}

package com.test.repository;

import com.test.model.Book;
import com.test.model.StatusBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> getAllByName(String Name);

    List<Book> getAllByAuthot (String Authot);

    List<Book> getAllByStatus(StatusBook Status);

    Book getAllByResevedBook (String ResevedBook);
}

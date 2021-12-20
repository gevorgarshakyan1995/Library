package com.test.repository;

import com.test.model.Book;
import com.test.model.StatusBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select u from Book u where u.name = :Name")
    List<Book> getAllByName(String Name);

    @Query(value = "select u from Book u where u.authot = :Authot")
    List<Book> getAllByAuthot (String Authot);

    @Query(value = "select u from Book u where u.status = :Status")
    List<Book> getAllByStatus(StatusBook Status);

    @Query(value = "select u from Book u where u.ResevedBook = :ResevedBook")
    Book getAllByResevedBook (String ResevedBook);
}

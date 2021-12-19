package com.test.repository;

import com.test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> getAllByName(String Name);

    List<Book> getAllByAuthot (String Authot);
}

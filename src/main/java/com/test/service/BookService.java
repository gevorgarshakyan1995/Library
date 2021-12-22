package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Book;
import com.test.model.StatusBook;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {
    public List<Book> getall(Integer no,String sort) throws NotFoundException;

    void save(Book book) throws NotFoundException;

    void DeleteById(int id);

    Book getById(int id) throws NotFoundException;

    List<Book> getAllByName(String name);

    List<Book> getAllByAuthot (String Authot);

    List<Book> getAllByStatus(StatusBook Status);

    Book getAllByResevedBook (String ResevedBook);




}

package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Book;
import com.test.model.User;
import com.test.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getall() throws NotFoundException {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) throws NotFoundException {
        bookRepository.save(book);
    }

    @Override
    public void DeleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getById(int id) throws NotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            throw new NotFoundException("not id");
        }
        return optionalBook.get();
    }

    @Override
    public List<Book> getAllByName(String name) {
        return bookRepository.getAllByName(name);
    }

    @Override
    public List<Book> getAllByAuthot(String Authot) {
        return bookRepository.getAllByAuthot(Authot);
    }
}

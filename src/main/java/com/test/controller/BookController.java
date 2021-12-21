package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.Book;
import com.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getall() throws NotFoundException {
        return bookService.getall();
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @PostMapping
    void save(@RequestBody Book book) throws NotFoundException {
        bookService.save(book);
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @DeleteMapping
    public void DeleteById(int id){
        bookService.DeleteById(id);
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable int id) throws NotFoundException{
        return bookService.getById(id);
    }

    @GetMapping("/get-name")
    public List<Book> getAllByName(String name){
        return bookService.getAllByName(name);
    }

    @GetMapping("/get-author")
    public List<Book> getAllByAuthot (String Authot){
        return bookService.getAllByAuthot(Authot);
    }


}

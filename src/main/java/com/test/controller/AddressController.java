package com.test.controller;

import com.test.exception.NotFoundException;
import com.test.model.Address;
import com.test.service.AddresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddresService addresService;

    @GetMapping
    List<Address> getAll(@RequestParam("no") Integer no, @RequestParam("sort") String sort) {  // no = page nomer
        return addresService.getall(no, sort);
    }

    @GetMapping("{id}")
    Address getById(@PathVariable int id) throws NotFoundException {
        return addresService.getById(id);
    }


    @DeleteMapping
    public void DeleteById(@RequestParam("id") int id) {
        addresService.DeleteById(id);
    }

    @PutMapping
    public void Urdate(@RequestParam("id") int id, @RequestParam("models") String models) throws NotFoundException {
        addresService.Urdate(id, models);

    }

    @PostMapping
    void save(@RequestBody Address address) {
        addresService.save(address);
    }
}

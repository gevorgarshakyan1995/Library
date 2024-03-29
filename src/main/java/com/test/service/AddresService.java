package com.test.service;

import com.test.exception.NotFoundException;
import com.test.model.Address;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AddresService {

    public List<Address> getall(Integer no,String sort);

    public Address save(Address address);

    public void DeleteById(int id);

    public Address getById(int id) throws NotFoundException;

    public void Urdate(int id, String number) throws NotFoundException;

    public List<Address> getAllBynumbers(String numbers);

    public Address getAllByNumberAndCityAndStreet(Address address);
}

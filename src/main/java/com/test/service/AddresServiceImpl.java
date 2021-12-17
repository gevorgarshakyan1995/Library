package com.test.service;


import com.test.exception.NotFoundException;
import com.test.model.Address;
import com.test.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddresServiceImpl implements AddresService {


    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getall() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional//(propagation = Propagation.REQUIRES_NEW)
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void DeleteById(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address getById(int id) throws NotFoundException {
        Optional<Address> optional = addressRepository.findById(id);

        if (!optional.isPresent()) {
            throw new NotFoundException("not id address");
        }
        return optional.get();
    }

    @Override
    public void Urdate(int id, String number) throws NotFoundException {
        Address address = getById(id);
        address.setNumber(number);
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAllBynumbers(String numbers) {
        return addressRepository.getAllByNumber(numbers);
    }

    @Override
    public Address getAllByNumberAndCityAndStreet(Address address) {
        String nnumber = address.getNumber();
        String city =address.getCity();
        String Street = address.getStreet();
        address=addressRepository.getAllByNumberAndCityAndStreet(nnumber,city,Street);
        return address;

    }
}
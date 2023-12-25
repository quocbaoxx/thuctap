package com.bao.example.thuctap.services;

import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    public List<Contact> findAll();


    public Optional<Contact> findContact(Integer id);

    public  Contact save(Contact contact);

    public Integer  delete(Contact contact);

    public   Contact update(Contact contact);


    List<Contact> sortByTotal();

    List<Contact> sortTotalGroup();
}

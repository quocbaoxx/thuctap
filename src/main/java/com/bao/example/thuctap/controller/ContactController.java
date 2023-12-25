package com.bao.example.thuctap.controller;

import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.Contact;
import com.bao.example.thuctap.repositories.CategoryRepository;
import com.bao.example.thuctap.repositories.ContactRepository;
import com.bao.example.thuctap.services.CategoryService;
import com.bao.example.thuctap.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public ResponseEntity<List<Contact>> getAll(){
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Contact> getCategory(@PathVariable("id") Integer id){

        Optional<Contact> category = contactService.findContact(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    //2.2 sortbytotal

    @GetMapping("/sortbytotal")
    public ResponseEntity<List<Contact>> getSortByTotal(){
        return ResponseEntity.ok(contactService.sortByTotal());
    }


//    2.3 sortbytotalgroup
    @GetMapping("/totalgroup")
    public ResponseEntity<List<Contact>> getTotalGoup(){
        return ResponseEntity.ok(contactService.sortTotalGroup());
    }



    @PostMapping
    public ResponseEntity<Contact> save(@RequestBody  Contact contact){
        return  ResponseEntity.ok(contactService.save(contact));
    }


//    2.1 update
    @PutMapping
    public  ResponseEntity<Contact> update(@RequestBody Contact contact){
        return  ResponseEntity.ok(contactService.update(contact));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        Contact contactToDelete = contactRepository.findById(id).orElseThrow();
        contactService.delete(contactToDelete);

        return new  ResponseEntity<>(HttpStatus.OK);
    }



}

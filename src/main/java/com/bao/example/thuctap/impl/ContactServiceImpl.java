package com.bao.example.thuctap.impl;

import com.bao.example.thuctap.dto.CategoryDTO;
import com.bao.example.thuctap.model.Category;
import com.bao.example.thuctap.model.Contact;
import com.bao.example.thuctap.repositories.ContactRepository;
import com.bao.example.thuctap.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository contactRepository;




    @Override
    public List<Contact> sortByTotal(){
        List<Contact> contacts = contactRepository.findAll();

        int n = contacts.size();
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (contacts.get(i - 1).getTotal().compareTo(contacts.get(i).getTotal()) > 0) {

                    Contact temp = contacts.get(i - 1);
                    contacts.set(i - 1, contacts.get(i));
                    contacts.set(i, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);

        return contacts;

    }

    @Override
    public List<Contact> sortTotalGroup() {
        List<Contact> contacts = contactRepository.findAll();

        Map<BigDecimal, List<String>> bigDecimalListMap = new HashMap<>();

        for (Contact contact : contacts){ // 1 : a; 1 : b; 3: c; 4: d; 5: af; 3: âf
            BigDecimal total = contact.getTotal();
            String name = contact.getName();

            if (bigDecimalListMap.containsKey(total)){
                bigDecimalListMap.get(total).add(name);
            }else {
                List<String> nameList = new ArrayList<>();
                nameList.add(name);
                bigDecimalListMap.put(total, nameList);
            }

        }

//        List<Contact> result = new ArrayList<>();
//        for (Map.Entry<BigDecimal, List<String>> entry : bigDecimalListMap.entrySet()) {
//            BigDecimal totalValue = entry.getKey();
//            List<String> names = entry.getValue();
//
//            for (String name : names) {
//                result.add(new Contact(name, totalValue));
//            }
//        }

        return contacts;
    }





    @Override
    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findContact(Integer id){
        return contactRepository.findById(id);
    }

    @Override
    public  Contact save(Contact contact){
        contact.setStatus(true);
        return  contactRepository.save(contact);
    }


    @Override
    public  Contact update(Contact contact){
        return  contactRepository.save(contact);
    }



    @Override
    public Integer delete(Contact contact){
        contact.setStatus(Boolean.FALSE);
        Contact deletedContact = contactRepository.save(contact);
        return  deletedContact.getId();

    }

}

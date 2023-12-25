package com.bao.example.thuctap.repositories;


import com.bao.example.thuctap.model.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductimageRepository  extends JpaRepository<Productimage, Integer> {
}

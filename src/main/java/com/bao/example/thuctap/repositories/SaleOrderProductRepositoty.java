package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.model.SaleOder;
import com.bao.example.thuctap.model.SaleOderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderProductRepositoty extends JpaRepository<SaleOderProduct, Integer> {
}

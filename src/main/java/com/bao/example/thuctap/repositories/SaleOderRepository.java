package com.bao.example.thuctap.repositories;

import com.bao.example.thuctap.model.SaleOder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOderRepository extends JpaRepository<SaleOder, Integer> {
}

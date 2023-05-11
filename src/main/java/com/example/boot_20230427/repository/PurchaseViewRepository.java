package com.example.boot_20230427.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.PurchaseView;

@Repository
public interface PurchaseViewRepository extends JpaRepository<PurchaseView, BigDecimal>  {
    
}

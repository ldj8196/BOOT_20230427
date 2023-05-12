package com.example.boot_20230427.repository.library;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.library.Checkout2;

@Repository
public interface Checkout2Repository extends JpaRepository<Checkout2,BigInteger> {
    
}

package com.example.boot_20230427.repository.library;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.library.Library2;

@Repository
public interface Library2Repository extends JpaRepository<Library2, BigInteger> {
    
}

package com.example.boot_20230427.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Reply1;

@Repository
public interface Reply1Repository extends JpaRepository<Reply1, Long> {
    
}

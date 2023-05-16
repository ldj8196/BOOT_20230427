package com.example.boot_20230427.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.library.Student2;
import com.example.boot_20230427.entity.library.Student2Projection;

@Repository
public interface Student2Repository extends JpaRepository<Student2,String> {
    
    long countByEmail(String email);

    Student2Projection findByEmail(String email);
    
}

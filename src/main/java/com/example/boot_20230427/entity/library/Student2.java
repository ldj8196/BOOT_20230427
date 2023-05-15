package com.example.boot_20230427.entity.library;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "STUDENT2")
public class Student2 {
    @Id
    private String email;

    private String name;

    private String phone;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;
}

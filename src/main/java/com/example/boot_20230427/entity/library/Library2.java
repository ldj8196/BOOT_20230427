package com.example.boot_20230427.entity.library;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "LIBRARY2")
@SequenceGenerator(name = "SEQ_LIBRARY2_CODE",sequenceName = "SEQ_LIBRARY2_CODE",initialValue = 1,allocationSize = 1)
public class Library2 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIBRARY2_CODE")
    private BigInteger code;

    private String name;

    private String address;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;

}

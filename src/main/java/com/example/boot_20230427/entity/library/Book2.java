package com.example.boot_20230427.entity.library;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BOOK2")
@SequenceGenerator(name = "SEQ_BOOK2_CODE",sequenceName = "SEQ_BOOK2_CODE",initialValue = 1,allocationSize = 1)
public class Book2 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_BOOK2_CODE")
    @Column(name = "CODE")
    private BigInteger code;

    private String name;

    private String writer;

    private BigInteger price;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE",referencedColumnName = "LCODE")
    private Library2 library2;
}

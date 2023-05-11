package com.example.boot_20230427.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "MENU1")
@SequenceGenerator(name = "SEQ_MENU1_NO",sequenceName = "SEQ_MENU1_NO",initialValue = 1,allocationSize = 1)
public class Menu1 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU1_NO")
    private BigInteger no;

    private String name;

    private BigInteger price;

    @Lob
    @ToString.Exclude
    private byte[] imagedata;

    private BigInteger imagesize;

    private String imagename;

    private String imagetype;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "RPHONE", referencedColumnName = "PHONE"),
        @JoinColumn(name = "RNO", referencedColumnName = "NO")
    })
    private Restaurant1 restaurant1;
}

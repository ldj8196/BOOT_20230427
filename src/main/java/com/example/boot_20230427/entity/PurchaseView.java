package com.example.boot_20230427.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Immutable
@Data
@Table(name = "PURCHASEVIEW")
public class PurchaseView {
    
    @Id
    @Column(name = "NO")
    private BigDecimal no;

    @Column(name = "CNT")
    private BigDecimal cnt;

    @Column(name = "ITEMPRICE")
    private BigDecimal itemprice;

    @Column(name = "ITEMNO")
    private BigDecimal itemno;

    @Column(name = "CUSTOMERID")
    private String customerid;

    @Column(name = "CUSTOMERNAME")
    private String customername;

    @Column(name = "ITEMNAME")
    private String itemname;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss.SSS")
    @Column(name = "REGDATE")
    private Date regdate;

}

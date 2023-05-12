package com.example.boot_20230427.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "MEMBERINFO1")
@Data
public class Memberinfo1 {
    
    @Id
    @Column(name = "ID1")
    private String id1;

    @MapsId // 컬럼을 줄이고 ID1컬럼 하나만 생성(외래키 제약)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID1")
    private Member1 member1;

    private String info;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;
}

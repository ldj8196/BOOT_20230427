package com.example.boot_20230427.entity.library;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CHECKOUT2")
@SequenceGenerator(name = "SEQ_CHECKOUT2_CODE",sequenceName = "SEQ_CHECKOUT2_CODE",initialValue = 1,allocationSize = 1)
public class Checkout2 {
    
}

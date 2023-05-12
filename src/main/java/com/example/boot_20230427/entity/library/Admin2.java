package com.example.boot_20230427.entity.library;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ADMIN2")
@SequenceGenerator(name = "SEQ_ADMIN2_NO",sequenceName = "SEQ_ADMIN2_NO",initialValue = 1,allocationSize = 1)
public class Admin2 {
    
}

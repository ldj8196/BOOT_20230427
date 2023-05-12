package com.example.boot_20230427.entity;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant1ID implements Serializable {
    private BigInteger no;
    private String phone;
}

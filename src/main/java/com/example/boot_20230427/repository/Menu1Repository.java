package com.example.boot_20230427.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Menu1;

@Repository
public interface Menu1Repository extends JpaRepository<Menu1, BigInteger>{

    List<Menu1> findByRestaurant1_no(BigInteger no);

    List<Menu1> findByRestaurant1_noOrderByNoAsc(BigInteger no);
    
}

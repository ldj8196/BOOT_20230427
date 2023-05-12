package com.example.boot_20230427.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Menu1;
import com.example.boot_20230427.entity.Menu1ImageProjection;

@Repository
public interface Menu1Repository extends JpaRepository<Menu1, BigInteger>{

    // 이미지 번호가 전달되면 이미지 정보(3개 반환)
    Menu1ImageProjection findByNo(BigInteger no);

    List<Menu1> findByRestaurant1_no(BigInteger no);

    List<Menu1> findByRestaurant1_noOrderByNoAsc(BigInteger no);
    
}

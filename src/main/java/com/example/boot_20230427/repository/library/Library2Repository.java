package com.example.boot_20230427.repository.library;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.library.Library2;

@Repository
public interface Library2Repository extends JpaRepository<Library2, BigInteger> {

    // findBy + 컬럼명 = where + 컬럼
    // findAllBy where x
    List<Library2> findAllByOrderByNameAsc();

    // 연락처별 내림차순 정렬 조건 x
    List<Library2> findAllByOrderByPhoneDesc();
}

package com.example.boot_20230427.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Board1View;

@Repository
public interface Board1ViewRepository extends JpaRepository<Board1View, BigDecimal> {
    // AND
    List<Board1View> findByNoAndTitle(long no, String title);

    // OR
    List<Board1View> findByNoOrTitle(long no, String title);

    // noIN
    List<Board1View> findByNoIn(List<Long> no);

    // titleIN
    List<Board1View> findByTitleIn(List<String> title);
}

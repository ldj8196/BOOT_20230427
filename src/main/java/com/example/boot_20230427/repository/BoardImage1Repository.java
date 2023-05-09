package com.example.boot_20230427.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Boardimage1;

@Repository
public interface BoardImage1Repository extends JpaRepository<Boardimage1, Long>{

    // 추가, 수정, 삭제..

    // 조회
    // 변수명이 객체다 Board1_no ex) board1
    // 객체가 아니다 No, Name 등 컬럼명 작성
    // findBy변수명OrderBy변수명Asc

    // 게시글번호가 일치하는 것 중에서 이미지번호가 가장 적은 것을 반환
    // select * from boardimage1 where board1.no=13 order by no asc limit 1;
    public Boardimage1 findTop1ByBoard1_noOrderByNoAsc(Long no);

    // 게시글 번호가 일치하는 모든 이미지
    public List<Boardimage1> findByBoard1_noOrderByNoAsc(Long no);
}

package com.example.boot_20230427.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Board;

@Repository
public interface Board2Repository extends JpaRepository<Board, BigDecimal> {
    
    // select * from board order by no desc
    List<Board> findAllByOrderByNoDesc(Pageable pageable);
    // 전체개수
    long countBy();
    // title
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String title, Pageable pageable);
    long countByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    // content
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String content);
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String content, Pageable pageable);
    long countByContentIgnoreCaseContainingOrderByNoDesc(String content);
    // writer
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer);
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer, Pageable pageable);
    long countByWriterIgnoreCaseContainingOrderByNoDesc(String writer);
}

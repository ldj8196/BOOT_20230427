package com.example.boot_20230427.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Restaurant1;

@Repository
public interface Restaurant1Repository extends JpaRepository<Restaurant1, String> {
     // 식당 전체 목록 표시(페이지네이션, 연락처, 이름별, 종류별, 주소별 검색)
     List<Restaurant1> findAllByOrderByNoDesc(Pageable pageable);
     long countBy();
     // 연락처별
     List<Restaurant1> findByPhoneIgnoreCaseContainingOrderByNoDesc(String phone);
     List<Restaurant1> findByPhoneIgnoreCaseContainingOrderByNoDesc(String phone, Pageable pageable);
     long countByPhoneIgnoreCaseContainingOrderByNoDesc(String phone);
     // 이름별
     List<Restaurant1> findByNameIgnoreCaseContainingOrderByNoDesc(String name);
     List<Restaurant1> findByNameIgnoreCaseContainingOrderByNoDesc(String name, Pageable pageable);
     long countByNameIgnoreCaseContainingOrderByNoDesc(String name);
     // 종류별
     List<Restaurant1> findByTypeIgnoreCaseContainingOrderByNoDesc(String type);
     List<Restaurant1> findByTypeIgnoreCaseContainingOrderByNoDesc(String type, Pageable pageable);
     long countByTypeIgnoreCaseContainingOrderByNoDesc(String type);
     // 주소별
     List<Restaurant1> findByAddressIgnoreCaseContainingOrderByNoDesc(String address);
     List<Restaurant1> findByAddressIgnoreCaseContainingOrderByNoDesc(String address, Pageable pageable);
     long countByAddressIgnoreCaseContainingOrderByNoDesc(String address);
}   

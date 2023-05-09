package com.example.boot_20230427.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot_20230427.entity.Address1;

@Repository
public interface Address1Repository extends JpaRepository<Address1, Long> {
    
    // select ... where address
    List<Address1> findByAddress(String address);

    // select ... where postcode
    List<Address1> findByPostcode(String postcode);

    // select ... where address=? AND postcode=?
    List<Address1> findByAddressAndPostcode(String address, String postcode);

    // select ... where member1.id=? => member1은 객체이기 때문에 _를 이용해서 id값을 get
    List<Address1> findByMember1_idOrderByNoDesc(String id);

    // select count(*) from address1 
    long countByMember1_id(String id);

    // 페이지 네이션 기능 포함
    List<Address1> findByMember1_idOrderByNoDesc(String id, Pageable pageable);
}

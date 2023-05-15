package com.example.boot_20230427.restcontroller.library;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot_20230427.entity.library.Student2;
import com.example.boot_20230427.entity.library.Student2Projection;
import com.example.boot_20230427.repository.library.Student2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/student2")
@RequiredArgsConstructor
@Slf4j
public class RestStudent2Controller {
    final String format = "RestStudent2Controller => {}";
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    @Autowired Student2Repository s2Repository;

    // 이메일 중복확인용
    @GetMapping(value = "/emailcheck.json")
    public Map<String, Object> emailCheckGET(@RequestBody Student2 obj) {
        long chk = s2Repository.countByEmail(obj.getEmail());
        log.info(format, chk);
        Map<String, Object> retMap = new HashMap<>();
        try {
            if(chk == 0) {
                retMap.put("status", 200);
                retMap.put("result", "사용가능합니다");
            }
            else {
                retMap.put("status", 200);
                retMap.put("result", "중복입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status",-1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }
    // 이메일이 전달되면 회원의 이름, 연락처가 반환되는
    @GetMapping(value = "/selectone.json")
    public Map<String, Object> selectoneGET(@RequestBody Student2 obj) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            Student2Projection s = s2Repository.findByEmail(obj.getEmail());
            retMap.put("name", s.getName());
            retMap.put("phone", s.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status",-1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }
    @PostMapping(value = "/insert.json")
    public Map<String, Object> insertPOST(@RequestBody Student2 obj) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            log.info(format, obj);
            obj.setPassword(bcpe.encode(obj.getPassword()));
            s2Repository.save(obj);
            retMap.put("status",200);
        } catch (Exception e) { // 개발자용 debug
            e.printStackTrace();
            retMap.put("status",-1);
            retMap.put("error", e.getMessage());
            
        }
        return retMap;
    }
}

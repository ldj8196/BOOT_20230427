package com.example.boot_20230427.restcontroller.library;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    final JwUtil2 jwUtil2; // 컴포넌트 객체 생성
    @Autowired Student2Repository s2Repository;

    // 회원탈퇴, 비밀번호변경, 회원정보수정 ... 로그인이 되어야 되는 모든것.
    // 회원정보수정 => 토큰을 주세요. 검증해서 성공하면 정보수정을 진행
    @PostMapping(value = "/update.json")
    public Map<String, Object> updatePOST(
        @RequestBody Student2 obj, 
        @RequestHeader(name = "token") String token) {

        Map<String, Object> retMap = new HashMap<>();

        try {
            // 1. 토큰을 받아서 출력
            log.info(format, token);
            log.info(format, obj.toString());

            // 2. 실패시 전달값
            retMap.put("status", 0);

            // 2. 토큰을 검증
            Student2 obj1 = jwUtil2.checkJwt(token);
            if( obj1 != null) {
                // 1. 이메일을 이용해서 기존 데이터 가져오기
                Student2 obj2 =s2Repository.findById(obj1.getEmail()).orElse(obj1);
                // 2. obj2에 필요한 정보 저장하기
                obj2.setName(obj.getName());
                obj2.setPhone(obj.getPhone());
                // 3. obj2를 다시 저장하기
                s2Repository.save(obj2);

                retMap.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }

    @PostMapping(value = "/login.json")
    public Map<String, Object> loginPOST(@RequestBody Student2 student2) {
    Map<String, Object> retMap = new HashMap<>();

        try {
            // 1. 이메일, 암호 전송 확인
            log.info(format, student2.toString());

            // 2. 이메일을 이용해서 정보를 가져옴.
            Student2 retStudent2 = s2Repository.findById(student2.getEmail()).orElse(null);
            
            // 3. 실패시 전송할 데이터
            retMap.put("status", 0);

            // 4. 암호가 일치하는 지 확인 => 전송된 hash되지 않은 암호와 DB에 해시된 암호 일치 확인
            if( bcpe.matches(student2.getPassword(), retStudent2.getPassword())) {
                retMap.put("status", 200);
                retMap.put("token", jwUtil2.createJwt(retStudent2.getEmail(), retStudent2.getName()));
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }

        return retMap;
    }

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

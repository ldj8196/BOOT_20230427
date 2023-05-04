package com.example.boot_20230427.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot_20230427.dto.Member;
import com.example.boot_20230427.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
@Slf4j
public class RestMemberController {

    final JwUtil jwtUtil; // Component 객체 생성
    final String format = "RestMemberController => {}";
    final MemberService mService;
    final BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
    // 로그인 => 로그인 성공시 세션에 정보를 저장하고 다른페이지에서 확인

    // rest로그인 => 토큰을 발행함.(토큰에는 로그인 사용자 정보 만료시간이 포함되어 있음.)
    // 127.0.0.1:9090/ROOT/member/api/login.json => {"id":"c3", "password":"a"}
    @PostMapping(value = "/login.json")
    public Map<String,Object> loginPOST(@RequestBody Member member) {
        log.info(format, member);
        // 아이디를 이용해서 회원정보 가져오기
        Member retMember = mService.selectMemberOne1(member.getId());
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("result", 0);
        if(retMember != null) {
            // 가져온 암호와 전달된 암호가 일치하는지 확인
            if(bcpe.matches(member.getPassword(), retMember.getPassword())) {
                // 암호가 일치하면 토큰 발행
                retMap.put("result", 1);
                retMap.put("token", jwtUtil.generateToken(member.getId(), "customer"));
            }
        }

        return retMap;

    }

    @PostMapping(value = "/join.json")
    public Map<String, Integer> joinPOST(@RequestBody Member member) {
        log.info(format,member);
        int ret = mService.memberJoin(member);
        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("result", ret);
        return retMap;
    }
}

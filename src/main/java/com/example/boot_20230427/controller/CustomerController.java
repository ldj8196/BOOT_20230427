package com.example.boot_20230427.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot_20230427.dto.Member;
import com.example.boot_20230427.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    
    final String format = "CustomerController => {}";
    
    @Autowired UserDetailsService uService;

    @Autowired MemberService mService;

    @GetMapping(value = "/home.do")
    public String homeGET() {
        return "/customer/home";
    }
    @PostMapping(value = "/home.do")
    public String homePOST(
        @RequestParam(name = "menu", defaultValue = "0", required = false) int menu,
        @RequestParam(name = "currentpassword", required = false) String currentpassword,
        @AuthenticationPrincipal User user,
        HttpServletRequest request,
        HttpServletResponse response ) {
        log.info(format, menu);

        if(menu==1) {
            log.info(format, user.getUsername());

            if(user.getUsername() != null ) {
                UserDetails userdetails = uService.loadUserByUsername(user.getUsername());
                Member member = new Member();
                member.setId(userdetails.getUsername());
                member.setPassword(userdetails.getPassword());
                member.setAge(Integer.parseInt(request.getParameter("age")));
                member.setName(request.getParameter("name"));
                log.info(format, member);
                int ret = mService.updateMemberOne(member);
                if(ret == 1) {
                    return "redirect:/home.do";
                }
            }
            return "redirect:/customer/home.do?menu=1";
        }

        else if(menu==2) {
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            UserDetails userdetails = uService.loadUserByUsername(user.getUsername());
            log.info(format, userdetails.getPassword());
            // 아이디 정보를 이용해서 db에서 1명 조회
            log.info(format,user.getUsername());
            // 조회된 정보의 아이디와 사용자가 입력한 아이디를 matches로 비교
            // 비밀번호 확인 => matches(바꾸기전비번, 해시된 비번)
            log.info(format, bcpe.matches(request.getParameter("password"), userdetails.getPassword()));

            if(bcpe.matches(request.getParameter("password"), userdetails.getPassword())) {
                Member member = new Member();
                member.setId(user.getUsername());
                member.setPassword(userdetails.getPassword());
                member.setNewpassword(bcpe.encode(request.getParameter("newpassword")));
                int ret = mService.updateMemberpassword(member);
                log.info(format, ret);
            }
            return "redirect:/customer/home.do?menu=2";
        }

        else if(menu==3) {
            // 아이디 정보를 이용해서 db에서 1명 조회
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            UserDetails userdetails = uService.loadUserByUsername(user.getUsername());
            log.info(format, currentpassword);
            // 조회된 정보와 현재 암호가 일치하는지 matches로 비교
            if(bcpe.matches(currentpassword, userdetails.getPassword())) {
                Member member = new Member();
                member.setId(user.getUsername());
                member.setPassword(userdetails.getPassword());
                int ret = mService.deleteMemberOne(member);
                // 컨트롤러에서 logout처리하기
                if(ret == 1) {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    if (auth != null) {
                        new SecurityContextLogoutHandler().logout(request, response, auth);
                    }
                    return "redirect:/home.do";
                }
                
            }
            // 비교가 true이면 db에서 삭제

        }

        return "redirect:/customer/home.do";
    }

    @GetMapping(value = "/join.do")
    public String joinGET() {
        return "/customer/join";
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member member) {
        log.info(format, member); // 화면이 정확하게 표시되고 사용자가 입력한 항목을 member 객체에 저장했음을 확인
        
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(); // salt값을 자동으로 부여함.
        member.setPassword(bcpe.encode(member.getPassword())); // 기존 암호를 암호화 시켜서 다시 저장함
        int ret = mService.memberJoin(member);
        if(ret == 1) {
            return "redirect:joinok.do";
        }
        return "redirect:join.do"; // 주소창에 127.0.0.1:9090/ROOT/customer/join.do 엔터키 자동화
    }
    @GetMapping(value = "/joinok.do")
    public String joinokGET(){
        return "/customer/joinok";
    }


}

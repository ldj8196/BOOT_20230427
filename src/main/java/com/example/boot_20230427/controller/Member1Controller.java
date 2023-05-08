package com.example.boot_20230427.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot_20230427.entity.Member1;
import com.example.boot_20230427.repository.Member1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/member1")
@RequiredArgsConstructor
@Slf4j
public class Member1Controller {

    final String format = "Member1Controller => {}";
    final Member1Repository m1Repository; // 저장소 객체


    @GetMapping(value = "/update.do")
    public ModelAndView updateGET(@RequestParam(name = "id") String id) {
        Member1 member1 = m1Repository.findById(id).orElse(null);
        return new ModelAndView("/member1/update","member1",member1);
    }
    @PostMapping(value = "/update.do")
    public String updatePOST(@ModelAttribute Member1 member1) {
        try {
            log.info(format, member1.toString());
            // save => 기존데이터를 읽어서 기본키 값이 있으면 update 없으면 insert
            Member1 member2 = m1Repository.findById(member1.getId()).orElse(null);
            // 변경항목을 바꾼다
            member2.setName(member1.getName());
            member2.setAge(member1.getAge());
            // 다시 저장함( 기본키인 아이디 정보가 있어야함. 없으면 insert되어버림)
            m1Repository.save(member2);
            return "redirect:/member1/selectlist.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @PostMapping(value = "/delete.do")
    public String deletePOST(@RequestParam(name = "id") String id ) {
        try {
            m1Repository.deleteById(id);   
            return "redirect:/member1/selectlist.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    // model.addAttribute + return html 혼합
    @GetMapping(value = "/selectlist.do")
    public String selectListGET(
        @RequestParam(name = "text",defaultValue = "",required = false) String text, 
        @RequestParam(name = "page", defaultValue = "0",required = false) int page, 
        Model model
        ) {
        if(page == 0) { // 페이지 정보가 없으면 자동으로 page=1로 변경
            return "redirect:/member1/selectlist.do?text="+text+"&page=1";
        }
        // 페이지 네이션 만들기(페이지번호0부터, 가져올개수10개)
        // PageRequest pageRequest = PageRequest.of(page-1, 10);

        long total = m1Repository.countByNameContaining(text);

        List<Member1> list = m1Repository.selectByNameContainingPagenation(text, (page*10)-9, page*10);
        model.addAttribute("list", list);
        model.addAttribute("pages", (total-1)/10+1); // 페이지 수 
        return "/member1/selectlist";
    }

    @GetMapping(value = "/join.do")
    public String joinGET() {
        return "/member1/join"; //templates
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member1 member1) {
        log.info(format, member1.toString());
        m1Repository.save(member1);
        return "redirect:/member1/join.do";
    }
}

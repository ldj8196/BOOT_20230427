package com.example.boot_20230427.controller.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot_20230427.entity.Board1View;
import com.example.boot_20230427.repository.Board1ViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/board1view")
@RequiredArgsConstructor
@Slf4j 
public class Board1ViewController {
    
    final Board1ViewRepository b1vRepository;
    final String format = "Board1ViewController => {}";
    
    @GetMapping(value = "/selectlist.pknu")
    public String selectlistGET(
        Model model,
        @RequestParam(name = "num",defaultValue = "0") int num,
        @RequestParam(name = "no",defaultValue = "0") String no,
        @RequestParam(name = "title",defaultValue = "") String title
        ) {
        try {
            log.info(format, num);
            log.info(format, no);
            log.info(format, title);
            List<Board1View> list = new ArrayList<>();
            // num이 0 또는 없으면 전체
            if(num == 0 ) {
                list = b1vRepository.findAll();
            }
            // num이 1 이면 AND
            else if(num == 1) {
                list = b1vRepository.findByNoAndTitle(Long.parseLong(no), title);
            }
            // num이 2 이면 OR
            else if(num == 2) {
                list = b1vRepository.findByNoOrTitle(Long.parseLong(no), title);
            }
            // num이 3 이면 글번호 in
            else if(num == 3) {
                String[] str = no.split(",");
                List<Long> lstr = new ArrayList<>();
                for(int i=0;i<str.length;i++) {
                    lstr.add(Long.parseLong(str[i]));
                }
                list = b1vRepository.findByNoIn(lstr);
                log.info(format, str.length);
            }
            // num이 4 이면 제목 in
            else if(num == 4) {
                String[] strTitle = title.split(",");
                List<String> tstr = new ArrayList<>();
                for(int i=0;i<strTitle.length;i++) {
                    tstr.add(strTitle[i]);
                }
                list = b1vRepository.findByTitleIn(tstr);
            }

            log.info(format, list);
            model.addAttribute("list", list);
            return "/board1view/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

}

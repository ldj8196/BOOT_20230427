package com.example.boot_20230427.controller.jpa.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.boot_20230427.entity.library.Library2;
import com.example.boot_20230427.repository.library.Library2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/library2")
@RequiredArgsConstructor
@Slf4j
public class Library2Controller {
    
    final String format = "Library2Controller = > {}";
    @Autowired Library2Repository l2Repository;

    @GetMapping(value = "/selectlist.do")
    public String selectlistGET(Model model) {
        try {
            List<Library2> list =l2Repository.findAllByOrderByNameAsc();
            model.addAttribute("list", list);
            return "/library2/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value = "/insert.do")
    public String insertGET() {
        try {

            return "/library2/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/insert.do")
    public String insertPOST(@ModelAttribute Library2 obj) {
        try {
            log.info(format, obj);
            l2Repository.save(obj);
            return "redirect:/library2/insert.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }


}

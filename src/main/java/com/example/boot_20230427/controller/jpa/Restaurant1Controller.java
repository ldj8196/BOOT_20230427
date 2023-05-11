package com.example.boot_20230427.controller.jpa;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot_20230427.dto.Search;
import com.example.boot_20230427.entity.Restaurant1;
import com.example.boot_20230427.repository.Restaurant1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/restaurant1")
@RequiredArgsConstructor
@Slf4j
public class Restaurant1Controller {

    final String format = "Restaurant1Controller => {}";
    final Restaurant1Repository r1Repository;
    @Value("${restaurant1.pagetotal}") int PAGETOTAL;

    @GetMapping(value = "/insert.food")
    public String insertGET() {
        try {
            return "/restaurant1/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/insert.food")
    public String insertPOST(
        @RequestParam(name = "phone") String phone,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "address") String address,
        @RequestParam(name = "type") String type) {
        try {
            // 식당을 5개 등록하시오
            Restaurant1 r = new Restaurant1();
            r.setPhone(phone);
            r.setName(name);
            r.setAddress(address);
            if(type.equals("korean")) {
                r.setType("한식");
            }
            else if(type.equals("chinese")) {
                r.setType("중식");
            }
            else if(type.equals("western")) {
                r.setType("양식");
            }
            r1Repository.save(r);
            return "redirect:/restaurant1/selectlist.food";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    // 127.0.0.1:9090/ROOT/restaurant1/selectlist.food?page=1&type=phone&text=
    // 식당 전체 목록 표시(페이지네이션, 연락처, 이름별, 종류별, 주소별 검색)
    @GetMapping(value = "/selectlist.food")
    public String selectlistGET(
        Model model,
        @ModelAttribute Search obj) {
        try {
            // 페이지 네이션 => 페이지 번호가 0부터
            PageRequest pageRequest = PageRequest.of(obj.getPage()-1, PAGETOTAL);

            // 연락처 검색
            List<Restaurant1> list = r1Repository.findByPhoneIgnoreCaseContainingOrderByNoDesc(obj.getText(), pageRequest);
            long total = r1Repository.countByPhoneIgnoreCaseContainingOrderByNoDesc(obj.getText());

            if(obj.getType().equals("name")) { // 상호명
                list = r1Repository.findByNameIgnoreCaseContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByNameIgnoreCaseContainingOrderByNoDesc(obj.getText());
            }
            else if(obj.getType().equals("address")) { // 주소
                list = r1Repository.findByAddressIgnoreCaseContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByAddressIgnoreCaseContainingOrderByNoDesc(obj.getText());
            }
            else if(obj.getType().equals("type")) { // 일식, 양식 ... 종류로 검색
                list = r1Repository.findByTypeIgnoreCaseContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByTypeIgnoreCaseContainingOrderByNoDesc(obj.getText());
            }

            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/PAGETOTAL+1);
            model.addAttribute("search", obj);
            return "/restaurant1/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}

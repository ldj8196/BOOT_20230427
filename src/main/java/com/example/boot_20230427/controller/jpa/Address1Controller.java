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

import com.example.boot_20230427.entity.Address1;
import com.example.boot_20230427.entity.Address1Projection;
import com.example.boot_20230427.entity.Member1;
import com.example.boot_20230427.repository.Address1Repository;
import com.example.boot_20230427.repository.Member1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/address1")
@Slf4j
@RequiredArgsConstructor
public class Address1Controller {
    
    @Value("${address.pagetotal}") int PAGETOTAL;

    final String format = "Address1Controller = > {}";
    final Member1Repository m1Repository;
    final Address1Repository a1Repository;

    @GetMapping(value = "/selectlistprojection.do")
    public String selectlistprojectionGET(Model model) {
        try {
            List<Address1Projection> list = a1Repository.findAllByOrderByNoDesc(Address1Projection.class);
            for(Address1Projection obj : list ) {
                log.info(format, obj.getAddress() + "," + obj.getNo() + "," + obj.getNoAddress()+ "," + obj.getMember1().getId() + "," + obj.getMember1().getName());
            }
            model.addAttribute("list", list);
            return "/address1/selectlistprojection";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }


    @GetMapping(value = "/selectlist.do")
    public String selectlistGET(
        @RequestParam(name = "id") String id,
        @RequestParam(name = "page",defaultValue = "0") int page,
        Model model
        ) {
        try {
            if(page==0) {
                return "redirect:/address1/selectlist.do?id="+id+"&page=1";
            }
            // 회원 정보
            Member1 member1 = m1Repository.findById(id).orElse(null);
            log.info(format, member1.toString());
            model.addAttribute("obj", member1);
            // 전체 개수 가져오기
            long total = a1Repository.countByMember1_id(member1.getId());
            model.addAttribute("pages", (total-1)/PAGETOTAL+1 );

            // 페이지네이션 설정
            // 1페이지는 0
            PageRequest pageRequest = PageRequest.of((page-1), PAGETOTAL);

            List<Address1> addressList = a1Repository.findByMember1_idOrderByNoDesc(member1.getId(),pageRequest);

            model.addAttribute("address", addressList);

            return "/address1/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @PostMapping(value = "/insert.do")
    public String insertPOST(@ModelAttribute Address1 address1) {
        try {
            log.debug(format, address1.toString()); // 오류발생시점 stackoverflow
            a1Repository.save(address1);
            return "redirect:/address1/selectlist.do?id="+address1.getMember1().getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @PostMapping(value = "/delete.do")
    public String deletePOST(
        @RequestParam(name = "no1") long no,
        @RequestParam(name = "id1") String id
        ) {
        try {
            log.info(format, no);
            a1Repository.deleteById(no);
            return "redirect:/address1/selectlist.do?id="+id;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}

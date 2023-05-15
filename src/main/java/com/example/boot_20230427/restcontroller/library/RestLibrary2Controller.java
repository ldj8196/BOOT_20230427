package com.example.boot_20230427.restcontroller.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot_20230427.entity.library.Library2;
import com.example.boot_20230427.repository.library.Library2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/library2")
@RequiredArgsConstructor
@Slf4j
public class RestLibrary2Controller {
    
    final String format = "RestLibrary2Controller => {}";
    @Autowired Library2Repository l2Repository;


    // @RequestBody Library2 obj => 기본으로 보낼떄(json,xml)
    // @ModelAttribute Library2 obj => json,xml로 보내지 못할때 ex)이미지
    @GetMapping(value = "/selectlist.json")
    public Map<String, Object> selectlistGET() {
        Map<String,Object> retMap = new HashMap<>();

        try {
            List<Library2> list =l2Repository.findAllByOrderByNameAsc();
            retMap.put("status", 200);
            retMap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("status",-1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }


    @PostMapping(value = "/insert.json")
    public Map<String, Object> insertPOST(@RequestBody Library2 library2) {
        Map<String, Object> retMap = new HashMap<>();

        try {
            log.info(format, library2);
            l2Repository.save(library2);
            retMap.put("status",200);
        } catch (Exception e) { // 개발자용 debug
            e.printStackTrace();
            retMap.put("status",-1);
            retMap.put("error", e.getMessage());
            
        }
        return retMap;
    }


}

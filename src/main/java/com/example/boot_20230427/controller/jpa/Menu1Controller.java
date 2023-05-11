package com.example.boot_20230427.controller.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot_20230427.entity.Menu1;
import com.example.boot_20230427.repository.Menu1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/menu1")
@RequiredArgsConstructor
@Slf4j
public class Menu1Controller {

    final String format = "Menu1Controller => {}";
    final Menu1Repository m1Repository;
    @Autowired ResourceLoader resourceLoader; // resources폴더의 파일을 읽기 위한 객체 생성
    @Value("${default.image}") String defaultImage;
    // 이미지 한개 출력
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam(name = "no", defaultValue = "0") long no) throws IOException {

        Menu1 obj = m1Repository.findById(BigInteger.valueOf(no)).orElse(null);
        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;

        if(obj != null){ // 이미지 존재 확인
            if(obj.getImagesize().longValue()>0) {
                headers.setContentType(MediaType.parseMediaType(obj.getImagetype()));
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(obj.getImagedata(), headers, HttpStatus.OK);
                return response;
            }
        }

        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource(defaultImage).getInputStream(); // exception 발생
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(is.readAllBytes(), headers, HttpStatus.OK);
    }
    @GetMapping(value = "/insert.food")
    public String insertGET(
        Model model,
        HttpServletRequest request,
        @RequestParam(name = "rno") long rno,
        @RequestParam(name = "rphone") String rphone) {
        try {
            List<Menu1> list = m1Repository.findByRestaurant1_no(BigInteger.valueOf(rno));
            // 전체 이미지
            List<String> imageList = new ArrayList<>();
            List<Menu1> list1 = m1Repository.findByRestaurant1_noOrderByNoAsc(BigInteger.valueOf(rno));
            if(!list1.isEmpty()) {
                for(Menu1 menu1 : list1) {
                    imageList.add(request.getContextPath()+"/menu1/image?no="+menu1.getNo());
                }
            }
            model.addAttribute("imageList", imageList);
            model.addAttribute("list", list);
            model.addAttribute("rphone", rphone);
            model.addAttribute("rno", rno);
            return "/menu1/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/insert.food")
    public String insertPOST(
        @ModelAttribute Menu1 menu,
        @RequestParam(name ="image") MultipartFile file) {
        try {
            log.info(format, menu.toString());
            menu.setImagesize(BigInteger.valueOf(file.getSize()));
            menu.setImagedata(file.getInputStream().readAllBytes());
            menu.setImagetype(file.getContentType());
            menu.setImagename(file.getOriginalFilename());
            log.info(format, menu.toString());
            m1Repository.save(menu);
            return "redirect:/restaurant1/selectlist.food";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}

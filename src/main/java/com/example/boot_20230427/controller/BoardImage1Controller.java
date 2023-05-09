package com.example.boot_20230427.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.example.boot_20230427.entity.Board1;
import com.example.boot_20230427.entity.Boardimage1;
import com.example.boot_20230427.repository.Board1Repository;
import com.example.boot_20230427.repository.BoardImage1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/boardimage1")
@RequiredArgsConstructor
@Slf4j
public class BoardImage1Controller {
    
    final String format = "BoardImage1Controller => {}";
    final Board1Repository b1Repository;
    final BoardImage1Repository bi1Repository;
    @Autowired ResourceLoader resourceLoader; // resources폴더의 파일을 읽기 위한 객체 생성
    @Value("${default.image}") String defaultImage;


    @GetMapping(value = "/selectlist.do")
    public String selectListGET(
        @RequestParam(name = "no") long no,
        Model model,
        HttpServletRequest request
        ) {
        try {
            // 게시글정보
            Board1 board1 = b1Repository.findById(no).orElse(null);
    
            // 대표이미지
            Boardimage1 image1 = bi1Repository.findTop1ByBoard1_noOrderByNoAsc(no);
            board1.setImageUrl(request.getContextPath()+"/boardimage1/image?no=0");
            if(image1 != null) {
                board1.setImageUrl(request.getContextPath()+"/boardimage1/image?no="+image1.getNo());
                log.info(format, image1.toString());
            }

            // 이미지도 포함하여 view로 전달
            model.addAttribute("board1", board1);

            // 전체이미지
            List<String> imageList = new ArrayList<>();
            List<Boardimage1> list1 = bi1Repository.findByBoard1_noOrderByNoAsc(no);
            if(!list1.isEmpty()) { // 리스트가 비어있는지 확인
                for(Boardimage1 br1 : list1) {
                    imageList.add(request.getContextPath()+"/boardimage1/image?no="+br1.getNo());
                }
                log.info(format, list1.toString());
            }
            model.addAttribute("imageList", imageList);
            return "/boardimage1/selectlist";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirct:/home.do";
        }
    }
    
    // 이미지 한개 출력
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image(@RequestParam(name = "no", defaultValue = "0") long no) throws IOException {

        Boardimage1 obj = bi1Repository.findById(no).orElse(null);
        HttpHeaders headers = new HttpHeaders(); // import org.springframework.http.HttpHeaders;

        if(obj != null){ // 이미지 존재 확인
            if(obj.getImageSize()>0) {
                headers.setContentType(MediaType.parseMediaType(obj.getImageType()));
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(obj.getImageData(), headers, HttpStatus.OK);
                return response;
            }
        }

        // 이미지가 없을 경우
        InputStream is = resourceLoader.getResource(defaultImage).getInputStream(); // exception 발생
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(is.readAllBytes(), headers, HttpStatus.OK);
    }
    
    @PostMapping(value = "/insertimage.do")
    public String insertimagePOST(
        @ModelAttribute Boardimage1 image1,
        @RequestParam(name = "tmpfile") MultipartFile file
        ) {
        try {
            log.info(format, image1.toString());
            image1.setImageSize(file.getSize());
            image1.setImageData(file.getInputStream().readAllBytes());
            image1.setImageType(file.getContentType());
            image1.setImageName(file.getOriginalFilename());
            log.info(format, image1.toString());
            bi1Repository.save(image1);
            return "redirect:/boardimage1/selectlist.do?no="+image1.getBoard1().getNo();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

}

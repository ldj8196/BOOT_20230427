package com.example.boot_20230427.controller.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot_20230427.entity.Board;
import com.example.boot_20230427.repository.Board2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/board2")
@RequiredArgsConstructor
@Slf4j
public class Board2Controller {
    
    final String format = "Board2Controller => {}";
    final Board2Repository b2Repository;
    final HttpSession httpSession;
    @Value("${board.pagetotal}") int PAGETOTAL;

    @GetMapping(value = "/selectlist.pknu")
    public String selectListGET(
        Model model,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "type", defaultValue = "") String type,
        @RequestParam(name = "text", defaultValue = "") String text) {
        try {
            if( page == 0 ) {
                return "redirect:/board2/selectlist.pknu?page=1&type=&text=";
            }
            List<Board> list = new ArrayList<>();
            PageRequest pageRequest = PageRequest.of((page-1), PAGETOTAL);
            long total;

            if(type.equals("title")) {
                list = b2Repository.findByTitleIgnoreCaseContainingOrderByNoDesc(text,pageRequest);
                total = b2Repository.countByTitleIgnoreCaseContainingOrderByNoDesc(text);
            }
            else if(type.equals("content")) {
                list = b2Repository.findByContentIgnoreCaseContainingOrderByNoDesc(text,pageRequest);
                total = b2Repository.countByContentIgnoreCaseContainingOrderByNoDesc(text);
            }
            else if(type.equals("writer")) {
                list = b2Repository.findByWriterIgnoreCaseContainingOrderByNoDesc(text,pageRequest);
                total = b2Repository.countByWriterIgnoreCaseContainingOrderByNoDesc(text);
            }
            else {
                list = b2Repository.findAllByOrderByNoDesc(pageRequest);
                total = b2Repository.countBy();
            }
            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1)/PAGETOTAL+1 );
            return "/board2/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/updatebatchaction.pknu")
    public String updatebatchactionPOST(
        @RequestParam(name = "no[]") long[] no,
        @RequestParam(name = "title[]") String[] title,
        @RequestParam(name = "content[]") String[] content,
        @RequestParam(name = "writer[]") String[] writer) {
        try {
            List<Board> list = new ArrayList<>();
            for(int i=0;i<no.length;i++) {
                // 1. no를 이용하여 기존 정보 가져오기
                Board board = b2Repository.findById(BigDecimal.valueOf(no[i])).orElse(null);
                // 2. 기존정보에 위에서 받은 제목, 내용, 작성자 변경하기
                board.setTitle(title[i]);
                board.setContent(content[i]);
                board.setWriter(writer[i]);
                //3. list에 담기
                list.add(board);
            }
            //4. 업데이트하기
            b2Repository.saveAll(list);
            return "redirect:/board2/selectlist.pknu";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board2/updatebatch.pknu";
            
        }
    }
    @SuppressWarnings("unchecked")
    @GetMapping(value = "/updatebatch.pknu")
    public String updatebatchGET(Model model) {
        try {
            List<BigDecimal> chk = (List<BigDecimal>)httpSession.getAttribute("chk[]");
            List<Board> list = b2Repository.findAllById(chk);
            model.addAttribute("list", list);
            return "/board2/updatebatch";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/updatebatch.pknu")
    public String updatebatchPOST(@RequestParam(name = "chk[]") List<BigDecimal> chk ) {
        try {
            httpSession.setAttribute("chk[]", chk);
            return "redirect:/board2/updatebatch.pknu";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    @PostMapping(value = "/deletebatch.pknu")
    public String deletebatchPOST(@RequestParam(name = "chk[]") List<BigDecimal> chk ) {
        try {
            log.info(chk.toString());
            b2Repository.deleteAllById(chk);
            return "redirect:/board2/selectlist.pknu";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @PostMapping(value = "/insertbatch.pknu")
    public String insertbatchPOST(
        @RequestParam(name = "title[]") String[] title,
        @RequestParam(name = "content[]") String[] content,
        @RequestParam(name = "writer[]") String[] writer
        ) {

        try {
            List<Board> list = new ArrayList<>();
            for(int i = 0; i<title.length;i++) {
                Board board = new Board();
                board.setTitle(title[i]);
                board.setContent(content[i]);
                board.setWriter(writer[i]);
                board.setHit(BigDecimal.valueOf(1));
                list.add(board);
            }
            log.info(format, list.toString());
            b2Repository.saveAll(list);
            return "redirect:/board2/selectlist.pknu";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }

    }

    @GetMapping(value = "/insertbatch.pknu")
    public String insertbatchGET(Model model) {
        try {
            return "/board2/insertbatch";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

}

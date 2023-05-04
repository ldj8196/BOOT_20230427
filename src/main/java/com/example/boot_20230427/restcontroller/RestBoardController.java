package com.example.boot_20230427.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot_20230427.dto.Board;
import com.example.boot_20230427.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // => html을 표시할 수 없음.  Map, Member, Board, List => 를 반환하면 자동으로 json 바꿔줌.
@RequestMapping(value = "/api/board")
@RequiredArgsConstructor
@Slf4j
public class RestBoardController {
    
    final String format = "RestBoard => {}";
    final BoardService bService;

    // 게시글 삭제
    @DeleteMapping(value = "/delete.json")
    public Map<String, Integer> deleteDelete(@RequestBody Board board) {
        log.info(format, board);

        int ret = bService.deleteBoardOne(board.getNo());

        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("result", ret);

        return retMap;
    }

    // 게시글 수정
    @PutMapping(value = "/update.json")
    public Map<String,Integer> updatePUT(@RequestBody Board board) {
        log.info(format, board);

        int ret = bService.updateBoardOne(board);
        Map<String,Integer> retMap = new HashMap<>();
        retMap.put("result", ret);
        return retMap;
    }

    // 게시글 조회수증가
    // 게시글 번호가 전달되면 update를 이용해서 게시글 조회수 증가 시키고 결과를 result:1, 또는 result:0
    @RequestMapping(value = "/updatehit.json", method = {RequestMethod.PUT} )
    public Map<String, Integer> updatehitPUT(@RequestBody Board board) {
        log.info(format, board);
        Board board1 = bService.selectBoardOne(board.getNo());
        int ret = bService.updatehitBoard(board1);
        log.info(format, ret);
        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("result", ret);
        return retMap;
    }

    //127.0.0.1:9090/ROOT/api/board/insert.json
    // 게시판 글쓰기 => 제목, 내용, 작성자 => {"title":"a", "content":"b", "writer":"c"} 데이터를 추가 = POST
    @RequestMapping(value = "/insert.json", method = {RequestMethod.POST})
    public Map<String,Integer> insertPOST( @RequestBody Board Board) {
        // 전송되는값 확인
        log.info(format,Board.toString());
        // DB에 추가하고 결과를 1또는 0으로 반환
        int ret = bService.insertBoardOne(Board);

        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("result", ret);
        return retMap;
    }


    // REACT 입장에서 데이터 주세요 = GET
    //127.0.0.1:9090/ROOT/api/board/select.json
    @GetMapping(value = "/select.json")
    public Map<String, String> selectGET()  {
        Map<String, String> retMap = new HashMap<>();
        retMap.put("result", "ok");
        return retMap;
    }

    // 다중 요청 가능
    @RequestMapping(value = "/selectlist.json", method = {RequestMethod.GET})
    public List<Board> requsetMethodName() {
        return bService.selectBoardList();
    }
}

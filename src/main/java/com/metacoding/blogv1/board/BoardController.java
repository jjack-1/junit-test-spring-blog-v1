package com.metacoding.blogv1.board;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 책임 : 요청잘받고 응답잘하고
@Controller // 컴퍼넌트 스캔 -> DS가 활용
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // update board_tb set title=?, content=? where id = ?
    // 주소로 받는 데이터는 전부다 where에 걸린다 (pk,uk는 /1로, 나머지는 ?쿼리스트링)
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, String title, String content) {
        boardService.게시글수정하기(id, title, content);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(String title, String content) {
        System.out.println("title: " + title + " content: " + content);
        boardService.게시글쓰기(title, content);
        return "redirect:/"; // 주소가 만들어져있으면 리다이렉션
    }

    // C -> Model -> V
    @GetMapping("/")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardService.게시글목록();
        request.setAttribute("models", boardList); // request 담기
        return "list"; // forward
    }

    @GetMapping("/board/{id}") // 패턴 매칭 /board/1,2,3
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        Board board = boardService.게시글상세보기(id);
        request.setAttribute("model", board);
        return "detail";
    }

    @GetMapping("/board/save-form") // 주소 (하이픈 사용)
    public String saveForm() {
        return "save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        Board board = boardService.게시글상세보기(id);
        request.setAttribute("model", board);
        return "update-form";
    }
}

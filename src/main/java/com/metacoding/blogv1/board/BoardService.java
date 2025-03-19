package com.metacoding.blogv1.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 책임 : 트랜잭션 처리, 비지니스 로직 처리
@Service // IoC
public class BoardService {

    private BoardRepository boardRepository;

    // DI : 의존성 주입 -> IoC로 부터 들고옴
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional // 트랜잭션 시작 -> 함수 내부가 다 수행되면 commit, 실패 rollback
    public void 게시글쓰기(String title, String content) {
        boardRepository.insert(title, content);
    }

    public List<Board> 게시글목록() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board 게시글상세보기(int id) {
        Board board = boardRepository.findById(id);
        if (board == null) throw new RuntimeException("이상한 주소좀 떄리지마");
        return boardRepository.findById(id);
    }

    @Transactional
    public void 게시글삭제(int id) {
        // 1. 게시글이 존재하는 확인
        Board board = boardRepository.findById(id);

        // 2. 삭제
        if (board == null) {
            throw new RuntimeException("게시글이 없는데 왜 삭제를 ㅠ");
        }

        boardRepository.deleteById(id);
    } // commit

    @Transactional
    public void 게시글수정하기(int id, String title, String content) {
        // 1. 게시글이 존재하는 확인
        Board board = boardRepository.findById(id);

        // 2. 삭제
        if (board == null) {
            throw new RuntimeException("게시글이 없는데 왜 수정을 ㅠ");
        }

        // 3. 수정
        boardRepository.update(id, title, content);
    }
}

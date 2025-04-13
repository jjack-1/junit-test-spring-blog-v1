package com.metacoding.blogv1.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findById(id);

        // eye
        System.out.println("id: " + board.getId());
        System.out.println("title: " + board.getTitle());
        System.out.println("content: " + board.getContent());
        System.out.println("createAt: " + board.getCreatedAt());
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // eye

        for (Board board : boardList) {
            System.out.print("id: " + board.getId());
            System.out.print(" title: " + board.getTitle());
            System.out.print(" content: " + board.getContent());
            System.out.println(" createAt: " + board.getCreatedAt());
        }
    }

    @Test
    public void insert_test() {
        // given
        String title = "제목99";
        String content = "내용99";

        // when
        boardRepository.insert(title, content);

        // eye
        List<Board> boardList = boardRepository.findAll();
        System.out.println("id: " + boardList.get(0).getId());
        System.out.println("title: " + boardList.get(0).getTitle());
        System.out.println("content: " + boardList.get(0).getContent());
        System.out.println("createAt: " + boardList.get(0).getCreatedAt());
        System.out.println("length: " + boardList.size());
    }

    @Test
    public void deleteById_test() {
        // given
        int id1 = 1;
        int id2 = 2;

        // when
        boardRepository.deleteById(id1);

        // eye
        Board board1 = boardRepository.findById(id1);
        Board board2 = boardRepository.findById(id2);
        List<Board> boardList = boardRepository.findAll();

        System.out.println(board1);
        System.out.println(board2);
        System.out.println("length: " + boardList.size());
    }

    @Test
    public void update_test() {
        // given
        int id = 1;
        String title = "제목 99";
        String content = "내용 99";

        // when
        boardRepository.update(id, title, content);

        // eye
        Board board = boardRepository.findById(id);
        System.out.println("id: " + board.getId());
        System.out.println("title: " + board.getTitle());
        System.out.println("content: " + board.getContent());
        System.out.println("createAt: " + board.getCreatedAt());
    }

}

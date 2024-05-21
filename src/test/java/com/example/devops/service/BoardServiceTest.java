package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring을 실행시킨다. bean에 있는 것 (service, repository 등등)을 사용하고 싶을 때 사용
class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insertUser() {
        int lenBefore = boardRepository.findAll().size();
        BoardRequest dto = new BoardRequest("Hi", "Hi");
        boardService.insertBoard(dto);
        List<Board> boards = boardRepository.findAll();
        int lenAfter = boards.size();
        Board lastOne = boards.get(lenAfter-1);

        assertEquals(lenBefore+1, lenAfter);
        assertEquals("Hi", lastOne.getName());
        assertEquals("Hi", lastOne.getText());

    }

    @Test
    void getAll() {
        int lenBefore = boardService.getAll().size();
        BoardRequest dto = new BoardRequest("Hi", "Hi");
        boardRepository.save(dto.toEntity());

        List<Board> boards = boardService.getAll();
        int lenAfter = boards.size();
        Board lastOne = boards.get(lenAfter-1);

        assertEquals(lenBefore+1, lenAfter);
        assertEquals("Hi", lastOne.getName());
        assertEquals("Hi", lastOne.getText());

    }

    @Test
    void getOneById() {
        BoardRequest dto = new BoardRequest("Hola", "Hola");
        boardRepository.save(dto.toEntity());

        Long lenAfter = (long) boardRepository.findAll().size();
        Board board = boardService.getOneById(lenAfter);

        assertEquals("Hola", board.getName());
        assertEquals("Hola", board.getText());
    }

    @Test
    void deleteById() {
        // 매칭되는 id가 있을 때
        // given
        Board board = new Board(null, "test", "test");
        boardRepository.save(board); // entity manager 가 null 값을 채워서 다시 변환해준다.
        Long id = board.getId();

        // when
        boardService.deleteById(id);

        // then
        assertEquals(0,boardRepository.findAll().size());

//        BoardRequest dto = new BoardRequest("Hola", "Hola");
//        boardRepository.save(dto.toEntity());
//
//        int len1 = boardRepository.findAll().size();
//        boardService.deleteById((long) len1);
//        int len2 = boardRepository.findAll().size();
//
//        assertEquals(len1-1, len2);
    }

    @Test
    void deleteById_fail() {
        // 매칭되는 id가 없을 때
        assertThrows(IllegalArgumentException.class, () -> boardService.deleteById(1000L));
    }
}
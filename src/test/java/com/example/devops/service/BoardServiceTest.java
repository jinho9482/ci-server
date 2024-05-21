package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        BoardRequest dto = new BoardRequest("Hola", "Hola");
        boardRepository.save(dto.toEntity());

        int len1 = boardRepository.findAll().size();
        boardService.deleteById((long) len1);
        int len2 = boardRepository.findAll().size();

        assertEquals(len1-1, len2);


    }
}
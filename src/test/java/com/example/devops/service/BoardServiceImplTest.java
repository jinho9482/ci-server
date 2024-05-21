package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @Mock
    private BoardRepository boardRepository;
    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    void getOneById() {
        Board board = new Board(1l, "test", "test");
        BDDMockito.given(boardRepository.findById(1l))
                .willReturn(Optional.of(board));


        Board byId = boardService.getOneById(1l);

//        행위 검증 (findById를 1번 진행했다.)
        Mockito.verify(boardRepository, Mockito.times(1)).findById(1l);
//        상태 검증
        assertEquals("test", byId.getName());
        assertEquals("test", byId.getText());
        assertNotNull(byId.getId());
    }
    @Test
    void getOneByIdNotExist() {
        BDDMockito.given(boardRepository.findById(1l)).willReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, ()->{
            boardService.getOneById(1l);
        });
        Mockito.verify(boardRepository,Mockito.times(1)).findById(1l);

    }

    @Test
    void getAll() {

        BDDMockito.given(boardRepository.findAll()).willReturn(
                List.of(new Board(1l,"test", "test"),new Board(2l,"test", "test")));

        List<Board> all = boardService.getAll();

        assertEquals(2, all.size());
        assertEquals("test", all.get(1).getName());
        Mockito.verify(boardRepository).findAll();
    }

    @Test
    void insertBoard() {
        BoardRequest request = new BoardRequest("test", "test");
        Board entity = request.toEntity();
        BDDMockito.given(boardRepository.save(entity))
                .willReturn(entity);

        boardService.insertBoard(request);

        Mockito.verify(boardRepository, Mockito.times(1)).save(entity);
        System.out.println("Hello");
    }
}
package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // spring을 실행시킨다. bean에 있는 것 (service, repository 등등)을 사용하고 싶을 때 사용
class BoardServiceTest {
    @InjectMocks
    private BoardServiceImpl boardServiceImpl;
    @Mock
    private BoardRepository boardRepository;

    @Test
    void deleteById() {
        // 매칭되는 id가 있을 때
        // given
        Long id = 1L;
        BDDMockito.doNothing().when(boardRepository).deleteById(id);
        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.of(new Board(1L, null, null)));
        // when
        boardServiceImpl.deleteById(id);

        // then


    }

    @Test
    void deleteById_fail() {
        // 매칭되는 id가 없을 때

        // given
        Long id = 1000L;

        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.empty());
        BDDMockito.doNothing().when(boardRepository).deleteById(id);
        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            boardServiceImpl.deleteById(id);
        });
//        Mockito.verify(boardRepository, Mockito.times(1)).findById(id);

    }
}
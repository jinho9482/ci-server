package com.example.devops.controller;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import com.example.devops.service.BoardService;
import com.example.devops.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @Autowired // 통신을 위함
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    void insertBoard() throws Exception {
        // given
        BoardRequest boardRequest = new BoardRequest("Jinho", "Hola");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(boardRequest);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAll() throws Exception {
        // given
        BDDMockito.given(boardService.getAll())
                .willReturn(List.of(
                        new Board(1L, "test1", "test1"),
                        new Board(2L, "test2", "test2"),
                        new Board(3L, "test3", "test3")
                ));
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1)) // 검증, $ : body를 의미
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code
                .andDo(MockMvcResultHandlers.print()); // 뭔가를 하는 것
    }

    @Nested
    class getOneById {
        @Test
        void success() throws Exception {
                // given
                Long id = 1L;
                BDDMockito.given(boardService.getOneById(id))
                        .willReturn(new Board(1L, "Jinho", "Hola"));
                // when & then
                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards/" + id))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print());
        }

//        @Test
//        void fail() throws Exception {
//            // given
//            Long id = 1L;
//            BDDMockito.given(boardService.getOneById(id))
//                    .willThrow(IllegalArgumentException.class);
//            // when & then
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards/" + id))
//                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                    .andDo(MockMvcResultHandlers.print());
//        }

    }
    @Test
    void deleteById() throws Exception{
        // given
        Long id = 1L;
        BDDMockito.doNothing().when(boardService).deleteById(id);
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/boards/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
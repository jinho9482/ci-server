package com.example.devops.controller;


import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
@CrossOrigin(allowedHeaders = "*",
        origins = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.OPTIONS})
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertBoard(@RequestBody BoardRequest dto) {
        boardService.insertBoard(dto);
    }

    @GetMapping
    public List<Board> getAll() {
        return boardService.getAll();
    }

    @GetMapping("/{id}")
    public Board getOneById(@PathVariable Long id) {
        return boardService.getOneById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
    }

}

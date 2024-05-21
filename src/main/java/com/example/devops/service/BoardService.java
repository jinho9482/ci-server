package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;

import java.util.List;

public interface BoardService {

    void insertBoard(BoardRequest dto);

   List<Board> getAll();

    Board getOneById(Long id);

    void deleteById(Long id);
}

package com.example.devops.service;

import com.example.devops.dto.BoardRequest;
import com.example.devops.entity.Board;
import com.example.devops.global.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public void insertBoard(BoardRequest dto) {
        boardRepository.save(dto.toEntity());
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Board getOneById(Long id) {
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteById(Long id) {
        boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        boardRepository.deleteById(id);
    }

    @Override
    public List<Board> getAllByNameAndText(BoardRequest boardRequest) {
        if (boardRequest.text() ==null || boardRequest.name()==null)
            return boardRepository.findAllByNameOrText(boardRequest.name(), boardRequest.text());
        return boardRepository.findAllByNameAndText(boardRequest.name(), boardRequest.text());
    }
}

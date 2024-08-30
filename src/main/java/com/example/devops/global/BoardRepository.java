package com.example.devops.global;


import com.example.devops.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByNameAndText(String name, String text);
    List<Board> findAllByNameOrText(String name, String text);

}

package com.example.devops.dto;

import com.example.devops.entity.Board;

public record BoardRequest(
        String name,
        String text
) {
    public Board toEntity() {
        return Board.builder().name(name).text(text).build();
    }
}

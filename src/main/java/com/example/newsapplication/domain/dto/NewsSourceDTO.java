package com.example.newsapplication.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsSourceDTO {
    private Long id;
    private String name;
}

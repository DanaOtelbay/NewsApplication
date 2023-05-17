package com.example.newsapplication.domain.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private Long sourceId;
    private Set<Long> topicsId;
}

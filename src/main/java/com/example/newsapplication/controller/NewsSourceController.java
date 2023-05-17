package com.example.newsapplication.controller;

import com.example.newsapplication.domain.dto.NewsSourceDTO;
import com.example.newsapplication.domain.dto.NewsTopicDTO;
import com.example.newsapplication.domain.model.NewsSource;
import com.example.newsapplication.service.NewsSourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/news-sources")
@AllArgsConstructor
@Slf4j
public class NewsSourceController {

    private final NewsSourceService newsSourceService;


    @GetMapping
    public ResponseEntity<List<NewsSourceDTO>> getAllNewsSources() {
        List<NewsSourceDTO> newsSources = newsSourceService.getAllNewsSources();
        return ResponseEntity.ok(newsSources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsSourceDTO> getNewsTopicById(@PathVariable Long id){
        try {
            NewsSourceDTO newsSource = newsSourceService.getNewsSourceById(id)
                    .orElseThrow(() -> new IllegalArgumentException("News Source not found with ID: " + id));
            return ResponseEntity.ok(newsSource);
        }catch (Exception e){
            return ResponseEntity.noContent().header("Content-Length", "0").build();
        }
    }

    @PostMapping
    public ResponseEntity<NewsSourceDTO> createNewsSource(@RequestBody NewsSourceDTO newsSourceDTO){
        NewsSourceDTO createdNewsSource =newsSourceService.createNewsSource(newsSourceDTO);
        System.out.println("News Source created: " + createdNewsSource);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsSource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsSourceDTO> updateNewsSource(@PathVariable Long id, @RequestBody NewsSourceDTO newsSourceDTO){
        newsSourceDTO.setId(id);
        NewsSourceDTO updatedNewsSource = newsSourceService.updateNewsSource(newsSourceDTO);
        return ResponseEntity.ok(updatedNewsSource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsSourceById(@PathVariable Long id){
        try{
            newsSourceService.deleteNewsSourceById(id);
            System.out.println("News Source deleted with ID: " + id);
        }catch (Exception e){
            System.out.println(String.valueOf(e));
        }
        return ResponseEntity.noContent().build();
    }
}
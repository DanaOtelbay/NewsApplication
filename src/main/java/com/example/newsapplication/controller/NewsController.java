package com.example.newsapplication.controller;

import com.example.newsapplication.domain.dto.NewsDTO;
import com.example.newsapplication.domain.model.News;
import com.example.newsapplication.repository.NewsRepository;
import com.example.newsapplication.service.NewsService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;

import java.util.*;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
@Slf4j
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/all")
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> news = newsService.getAllNews();
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNewsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<NewsDTO> news = newsService.getAllNewsWithPagination(page, size);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        try{
            NewsDTO news = newsService.getNewsById(id)
                    .orElseThrow( () -> new IllegalArgumentException("News not found with ID: "+ id) );
            return ResponseEntity.ok(news);
        }catch (Exception e){
            return ResponseEntity.noContent().header("Content-Length", "0").build();
        }
    }

    @GetMapping("/bysource/{sourceId}")
    public ResponseEntity<List<NewsDTO>> getNewsBySourceId(
            @PathVariable Long sourceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<NewsDTO> news = newsService.getNewsBySourceId(sourceId, page, size);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/bytopic/{topicId}")
    public ResponseEntity<List<NewsDTO>> getNewsByTopicId(
            @PathVariable Long topicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<NewsDTO> news = newsService.getNewsByTopicId(topicId, page, size);

        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO news){
        NewsDTO createdNews = newsService.createNews(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @RequestBody NewsDTO newsDTO){
        newsDTO.setId(id);
        NewsDTO updatedNews = newsService.updateNews(newsDTO);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id){
        try{
            newsService.deleteNews(id);
        }catch (Exception e){
            System.out.println(String.valueOf(e));
        }
        return ResponseEntity.noContent().build();
    }
}


package com.example.newsapplication.controller;

import com.example.newsapplication.domain.dto.NewsTopicDTO;
import com.example.newsapplication.domain.model.NewsTopic;
import com.example.newsapplication.service.NewsTopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/news-topics")
@AllArgsConstructor
@Slf4j
public class NewsTopicController {
    private final NewsTopicService newsTopicService;

    @GetMapping
    public ResponseEntity<List<NewsTopicDTO>> getAllNewsTopics() {
        List<NewsTopicDTO> newsTopics = newsTopicService.getAllNewsTopic();
        return ResponseEntity.ok(newsTopics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsTopicDTO> getNewsTopicById(@PathVariable Long id){
        try {
            NewsTopicDTO newsTopic = newsTopicService.getNewsTopicById(id)
                    .orElseThrow(() -> new IllegalArgumentException("News Topic not found with ID: " + id));
            return ResponseEntity.ok(newsTopic);
        }catch (Exception e){
            return ResponseEntity.noContent().header("Content-Length", "0").build();
        }
    }

    @PostMapping
    public ResponseEntity<NewsTopicDTO> createNewsTopic(@RequestBody NewsTopicDTO newsTopicDTO){
        NewsTopicDTO createdNewsTopic = newsTopicService.createNewsTopic(newsTopicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsTopicDTO> updateNewsTopic(@PathVariable Long id, @RequestBody NewsTopicDTO newsTopicDTO){
        newsTopicDTO.setId(id);
        NewsTopicDTO updatedNewsTopic = newsTopicService.updateNewsTopic(newsTopicDTO);
        return ResponseEntity.ok(updatedNewsTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsTopicById(@PathVariable Long id){
        try{
            newsTopicService.deleteNewsTopicById(id);
            System.out.println("News Topic deleted with id: " + id);
        }catch (Exception e){
            System.out.println(String.valueOf(e));
        }
        return ResponseEntity.noContent().build();
    }
}

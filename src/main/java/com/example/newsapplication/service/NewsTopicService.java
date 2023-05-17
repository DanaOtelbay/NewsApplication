package com.example.newsapplication.service;

import com.example.newsapplication.domain.dto.NewsTopicDTO;
import com.example.newsapplication.domain.model.NewsTopic;
import com.example.newsapplication.repository.NewsTopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsTopicService {
    private final NewsTopicRepository newsTopicRepository;

    public List<NewsTopicDTO> getAllNewsTopic(){
        List<NewsTopic> newsTopics = newsTopicRepository.findAll();
        return newsTopics.stream()
                .map(this::convertToNewsTopicDTO)
                .collect(Collectors.toList());
    }

    public Optional<NewsTopicDTO> getNewsTopicById(Long id){
        Optional<NewsTopic> newsTopic = newsTopicRepository.findById(id);
        return newsTopic.map(this::convertToNewsTopicDTO);
    }

    public NewsTopicDTO createNewsTopic(NewsTopicDTO newsTopicDTO){
        NewsTopic newsTopic = convertToNewsTopic(newsTopicDTO);
        NewsTopic savedNewsTopic = newsTopicRepository.save(newsTopic);
        return convertToNewsTopicDTO(savedNewsTopic);
    }

    public NewsTopicDTO updateNewsTopic(NewsTopicDTO newsTopicDTO){
        NewsTopic newsTopic = convertToNewsTopic(newsTopicDTO);
        NewsTopic updatedNewsTopic = newsTopicRepository.save(newsTopic);
        return convertToNewsTopicDTO(updatedNewsTopic);
    }

    public void deleteNewsTopicById(Long id){
        NewsTopic newsTopic = newsTopicRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("News Topic not found with ID: " + id) );
        newsTopicRepository.deleteById(id);
    }

    public NewsTopicDTO convertToNewsTopicDTO(NewsTopic newsTopic){
        NewsTopicDTO newsTopicDTO = new NewsTopicDTO();
        newsTopicDTO.setId(newsTopic.getId());
        newsTopicDTO.setName(newsTopic.getName());
        return newsTopicDTO;
    }

    public NewsTopic convertToNewsTopic(NewsTopicDTO newsTopicDTO){
        NewsTopic newsTopic = new NewsTopic();
        newsTopic.setId(newsTopicDTO.getId());
        newsTopic.setName(newsTopicDTO.getName());
        return newsTopic;
    }
}

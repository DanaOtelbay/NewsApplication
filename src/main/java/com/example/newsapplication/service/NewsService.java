package com.example.newsapplication.service;

import com.example.newsapplication.domain.dto.NewsDTO;
import com.example.newsapplication.domain.model.News;
import com.example.newsapplication.domain.model.NewsSource;
import com.example.newsapplication.domain.model.NewsTopic;
import com.example.newsapplication.repository.NewsRepository;
import com.example.newsapplication.repository.NewsSourceRepository;
import com.example.newsapplication.repository.NewsTopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsSourceRepository newsSourceRepository;
    private final NewsTopicRepository newsTopicRepository;

    public List<NewsDTO> getAllNews(){
        List<News> news = newsRepository.findAll();
        return news.stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> getAllNewsWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<News> newsPage = newsRepository.findAll(pageable);
        List<NewsDTO> newsDTOList = newsPage.getContent().stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
        return newsDTOList;
    }

    public List<NewsDTO> getNewsBySourceId(Long sourceId, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<News> newsPage = newsRepository.findBySourceId(sourceId, pageable);

        List<NewsDTO> newsDTOList = newsPage.getContent().stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
        return newsDTOList;
    }

    public List<NewsDTO> getNewsByTopicId(Long topicsId, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<News> newsPage = newsRepository.findByTopicsId(topicsId, pageable);

        List<NewsDTO> newsDTOList = newsPage.getContent().stream()
                .map(this::convertToNewsDTO)
                .collect(Collectors.toList());
        return newsDTOList;
    }

    public Optional<NewsDTO> getNewsById(Long id){
        Optional<News> news = newsRepository.findById(id);
        return news.map(this::convertToNewsDTO);
    }

    public NewsDTO createNews(NewsDTO newsDTO){
        News news = convertToNews(newsDTO);
        News savedNews = newsRepository.save(news);
        return convertToNewsDTO(savedNews);
    }

    public NewsDTO updateNews(NewsDTO newsDTO){
        News news = convertToNews(newsDTO);
        News savedNews = newsRepository.save(news);
        return convertToNewsDTO(savedNews);
    }

    public void deleteNews(Long id){
        News news = newsRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("News not found with id: " + id) );
        newsRepository.deleteById(id);
    }

    public NewsDTO convertToNewsDTO(News news){
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setContent(news.getContent());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setSourceId(news.getSource().getId());
        newsDTO.setTopicsId(news.getTopics().stream().map(NewsTopic::getId).collect(Collectors.toSet()));
        return newsDTO;
    }

    public News convertToNews(NewsDTO newsDTO){
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setSource(newsSourceRepository.getOne(newsDTO.getSourceId()));
        news.setTopics(getTopicsByIds(newsDTO.getTopicsId()));
        return news;
    }

    private Set<NewsTopic> getTopicsByIds(Set<Long> topicIds) {
        return topicIds.stream()
                .map(newsTopicRepository::getOne)
                .collect(Collectors.toSet());
    }
}

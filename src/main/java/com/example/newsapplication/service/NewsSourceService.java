package com.example.newsapplication.service;

import com.example.newsapplication.domain.dto.NewsSourceDTO;
import com.example.newsapplication.domain.model.NewsSource;
import com.example.newsapplication.repository.NewsSourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsSourceService {
    private final NewsSourceRepository newsSourceRepository;

    public List<NewsSourceDTO> getAllNewsSources(){
        List<NewsSource> newsSources =newsSourceRepository.findAll();
        return newsSources.stream()
                .map(this::convertToNewsSourceDTO)
                .collect(Collectors.toList());
    }

    public Optional<NewsSourceDTO> getNewsSourceById(Long id){
        Optional<NewsSource> newsSource = newsSourceRepository.findById(id);
        return newsSource.map(this::convertToNewsSourceDTO);
    }

    public NewsSourceDTO createNewsSource(NewsSourceDTO newsSourceDTO){
        NewsSource newsSource = convertToNewsSource(newsSourceDTO);
        NewsSource savedNewsSource = newsSourceRepository.save(newsSource);
        return convertToNewsSourceDTO(savedNewsSource);
    }

    public NewsSourceDTO updateNewsSource(NewsSourceDTO newsSourceDTO){
        NewsSource newsSource =convertToNewsSource(newsSourceDTO);
        NewsSource updatedNewSource =newsSourceRepository.save(newsSource);
        return convertToNewsSourceDTO(updatedNewSource);
    }

    public void deleteNewsSourceById(Long id){
        NewsSource newsSource = newsSourceRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("News Source not found with ID: " + id) );
        newsSourceRepository.deleteById(id);
    }

    private NewsSource convertToNewsSource(NewsSourceDTO newsSourceDTO){
        NewsSource newsSource = new NewsSource();
        newsSource.setId(newsSourceDTO.getId());
        newsSource.setName(newsSourceDTO.getName());
        return newsSource;
    }

    private NewsSourceDTO convertToNewsSourceDTO(NewsSource newsSource){
        NewsSourceDTO newsSourceDTO = new NewsSourceDTO();
        newsSourceDTO.setId(newsSource.getId());
        newsSourceDTO.setName(newsSource.getName());
        return newsSourceDTO;
    }

}

package com.example.newsapplication.repository;

import com.example.newsapplication.domain.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findBySourceId(Long sourceId, Pageable pageable);
    Page<News> findByTopicsId(Long topicsId, Pageable pageable);
}

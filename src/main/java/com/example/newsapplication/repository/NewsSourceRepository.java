package com.example.newsapplication.repository;

import com.example.newsapplication.domain.model.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}

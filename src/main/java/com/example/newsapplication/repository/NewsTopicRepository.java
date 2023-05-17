package com.example.newsapplication.repository;

import com.example.newsapplication.domain.model.NewsTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTopicRepository extends JpaRepository<NewsTopic, Long> {
}

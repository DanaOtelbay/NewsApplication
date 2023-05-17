package com.example.newsapplication.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String content;

    @OneToOne
    @JoinColumn(name = "source_id", nullable = false)
    private NewsSource source;

    @ManyToMany
    @JoinTable(
            name = "news_topic",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<NewsTopic> topics = new HashSet<>();
}

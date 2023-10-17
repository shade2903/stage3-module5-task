package com.mjc.school.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "comment")
@EntityListeners({AuditingEntityListener.class})
public class CommentModel implements BaseEntity<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_updated_date")
    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "news_id")
    private NewsModel news;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public NewsModel getNews() {
        return news;
    }

    public void setNews(NewsModel news) {
        this.news = news;
    }

    public CommentModel() {
    }

    public CommentModel(Long id,
                        String name,
                        LocalDateTime createdDate,
                        LocalDateTime lastUpdatedDate,
                        NewsModel news) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentModel that = (CommentModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastUpdatedDate, that.lastUpdatedDate) && Objects.equals(news, that.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdDate, lastUpdatedDate, news);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", news=" + news +
                '}';
    }
}

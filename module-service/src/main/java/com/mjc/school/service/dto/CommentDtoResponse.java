package com.mjc.school.service.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDtoResponse extends RepresentationModel<CommentDtoResponse> {
    private Long id;
    private String name;


    private LocalDateTime createdDate;

    private LocalDateTime lastUpdatedDate;

    private Long newsId;

    public CommentDtoResponse() {
    }

    public CommentDtoResponse(Long id,
                              String name,
                              LocalDateTime createDate,
                              LocalDateTime lastUpdateDate,
                              Long newsId) {
        this.id = id;
        this.name = name;
        this.createdDate = createDate;
        this.lastUpdatedDate = lastUpdateDate;
        this.newsId = newsId;
    }

    public Long getId() {
        return id;
    }

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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDtoResponse that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastUpdatedDate, that.lastUpdatedDate) && Objects.equals(newsId, that.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdDate, lastUpdatedDate, newsId);
    }

    @Override
    public String toString() {
        return "CommentDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createdDate +
                ", lastUpdateDate=" + lastUpdatedDate +
                ", newsId=" + newsId +
                '}';
    }
}

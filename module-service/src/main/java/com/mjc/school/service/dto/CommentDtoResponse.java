package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDtoResponse {
    private Long id;
    private String name;


    private LocalDateTime createdDate;

    private LocalDateTime lastUpdatedDate;

    private NewsDtoResponse newsDto;

    public CommentDtoResponse() {
    }

    public CommentDtoResponse(Long id,
                              String name,
                              LocalDateTime createDate,
                              LocalDateTime lastUpdateDate,
                              NewsDtoResponse newsDto) {
        this.id = id;
        this.name = name;
        this.createdDate = createDate;
        this.lastUpdatedDate = lastUpdateDate;
        this.newsDto = newsDto;
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

    public NewsDtoResponse getNewsDto() {
        return newsDto;
    }

    public void setNewsDto(NewsDtoResponse newsDto) {
        this.newsDto = newsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDtoResponse that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate) &&
                Objects.equals(newsDto, that.newsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdDate, lastUpdatedDate, newsDto);
    }

    @Override
    public String toString() {
        return "CommentDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createdDate +
                ", lastUpdateDate=" + lastUpdatedDate +
                ", newsDto=" + newsDto +
                '}';
    }
}

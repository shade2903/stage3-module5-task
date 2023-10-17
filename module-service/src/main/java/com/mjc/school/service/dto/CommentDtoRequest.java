package com.mjc.school.service.dto;

import java.util.Objects;

public class CommentDtoRequest {
    private Long id;
    private String name;

    private Long newsId;

    public CommentDtoRequest() {
    }

    public CommentDtoRequest(Long id, String name, Long newsId) {
        this.id = id;
        this.name = name;
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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDtoRequest that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(newsId, that.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, newsId);
    }

    @Override
    public String toString() {
        return "CommentDtoRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", newsId=" + newsId +
                '}';
    }
}

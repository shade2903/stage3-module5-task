package com.mjc.school.service.dto;

import com.mjc.school.service.validator.constraint.Max;
import com.mjc.school.service.validator.constraint.Min;
import com.mjc.school.service.validator.constraint.NotNull;
import com.mjc.school.service.validator.constraint.Size;

import java.util.Objects;

public class CommentDtoRequest {
    @Min(1)
    @Max(Long.MAX_VALUE)
    private Long id;
    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @Min(1)
    @Max(Long.MAX_VALUE)
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

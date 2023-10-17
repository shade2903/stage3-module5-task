package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class NewsDtoRequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private List<Long> tagsIds;


    public NewsDtoRequest(Long id, String title, String content, Long authorId, List<Long> tagsIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagsIds = tagsIds;
    }

    public NewsDtoRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Long> getTagsIds() {
        return tagsIds;
    }

    public void setTagsIds(List<Long> tagsIds) {
        this.tagsIds = tagsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsDtoRequest that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(authorId, that.authorId) && Objects.equals(tagsIds, that.tagsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, authorId, tagsIds);
    }

    @Override
    public String toString() {
        return "NewsDtoRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", tagsId=" + tagsIds +
                '}';
    }
}

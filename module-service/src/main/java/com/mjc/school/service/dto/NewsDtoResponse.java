package com.mjc.school.service.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class NewsDtoResponse extends RepresentationModel<NewsDtoResponse> {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private AuthorDtoResponse authorDto;

    private List<TagDtoResponse> tagsDto;

    private List<CommentDtoResponse> commentsDto;


    public NewsDtoResponse(Long id,
                           String title,
                           String content,
                           LocalDateTime createDate,
                           LocalDateTime lastUpdateDate,
                           AuthorDtoResponse authorDto,
                           List<TagDtoResponse> tagsDto,
                           List<CommentDtoResponse> commentsDto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorDto = authorDto;
        this.tagsDto = tagsDto;
        this.commentsDto = commentsDto;
    }

    public NewsDtoResponse() {
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public AuthorDtoResponse getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDtoResponse authorDto) {
        this.authorDto = authorDto;
    }

    public List<TagDtoResponse> getTagsDto() {
        return tagsDto;
    }

    public void setTagsDto(List<TagDtoResponse> tagsDto) {
        this.tagsDto = tagsDto;
    }

    public List<CommentDtoResponse> getCommentsDto() {
        return commentsDto;
    }

    public void setCommentsDto(List<CommentDtoResponse> commentsDto) {
        this.commentsDto = commentsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsDtoResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastUpdateDate, that.lastUpdateDate) &&
                Objects.equals(authorDto, that.authorDto) &&
                Objects.equals(tagsDto, that.tagsDto) &&
                Objects.equals(commentsDto, that.commentsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, content, createDate, lastUpdateDate, authorDto, tagsDto, commentsDto);
    }

    @Override
    public String toString() {
        return "NewsDtoResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorDto=" + authorDto +
                ", tagsDto=" + tagsDto +
                ", commentsDto=" + commentsDto +
                '}';
    }
}

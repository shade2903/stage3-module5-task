package com.mjc.school.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "news")
@EntityListeners({AuditingEntityListener.class})
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag", joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tags;

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommentModel> comments;


    public NewsModel() {
    }

    public NewsModel(Long id,
                     String title,
                     String content,
                     LocalDateTime createDate,
                     LocalDateTime lastUpdateDate,
                     AuthorModel author,
                     List<TagModel> tags,
                     List<CommentModel> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.author = author;
        this.tags = tags;
        this.comments = comments;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
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

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsModel newsModel)) return false;
        return Objects.equals(id, newsModel.id) &&
                Objects.equals(title, newsModel.title) &&
                Objects.equals(content, newsModel.content) &&
                Objects.equals(createDate, newsModel.createDate) &&
                Objects.equals(lastUpdateDate, newsModel.lastUpdateDate) &&
                Objects.equals(author, newsModel.author) &&
                Objects.equals(tags, newsModel.tags) &&
                Objects.equals(comments, newsModel.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, author, tags, comments);
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", author=" + author +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }
}


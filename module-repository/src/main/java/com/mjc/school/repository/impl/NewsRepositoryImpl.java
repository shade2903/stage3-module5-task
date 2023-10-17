package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.repository.query.NewsSearchQueryParams;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsRepositoryImpl extends AbstractDBRepository<NewsModel,Long> implements NewsRepository {
    @Override
    void updatedEntityFields(NewsModel entity, NewsModel updatedEntity) {
        entity.setTitle(updatedEntity.getTitle());
        entity.setContent(updatedEntity.getContent());
        entity.setAuthor(updatedEntity.getAuthor());
        entity.setTags(updatedEntity.getTags());
        entity.setComments(updatedEntity.getComments());
    }

    @Override
    public List<NewsModel> readBySearchParams(NewsSearchQueryParams searchQueryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchQueryParams.tagNames() != null || searchQueryParams.tagIds() != null) {
            Join<TagModel, NewsModel> newsTags = root.join("tags");
            if (searchQueryParams.tagNames() != null) {
                predicates.add(newsTags.get("name").in(searchQueryParams.tagNames()));
            }
            if (searchQueryParams.tagIds() != null) {
                predicates.add((Predicate) newsTags.get("id").in(searchQueryParams.tagIds()));
            }
        }

        if (searchQueryParams.authorName() != null) {
            Join<AuthorModel, NewsModel> newsAuthor = root.join("author");
            predicates.add(criteriaBuilder.equal(newsAuthor.get("name"), searchQueryParams.authorName()));
        }

        if (searchQueryParams.content() != null) {
            predicates.add(criteriaBuilder.like(root.get("content"), "%" + searchQueryParams.content() + "%"));
        }

        if (searchQueryParams.title() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQueryParams.title() + "%"));
        }

        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}

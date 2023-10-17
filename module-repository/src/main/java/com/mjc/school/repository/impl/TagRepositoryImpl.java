package com.mjc.school.repository.impl;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TagRepositoryImpl extends AbstractDBRepository<TagModel,Long> implements TagRepository {
    @Override
    public List<TagModel> readByNewsId(Long newsId) {
        TypedQuery<TagModel> typedQuery = entityManager
                .createQuery("SELECT t FROM TagModel t INNER JOIN t.news n WHERE n.id=:newsId", TagModel.class)
                .setParameter("newsId", newsId);
        return typedQuery.getResultList();
    }

    @Override
    void updatedEntityFields(TagModel entity, TagModel updatedEntity) {
        entity.setName(updatedEntity.getName());
        entity.setNews(updatedEntity.getNews());

    }
}

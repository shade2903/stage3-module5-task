package com.mjc.school.repository.impl;

import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.model.CommentModel;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryImpl extends AbstractDBRepository<CommentModel, Long> implements CommentRepository {
    @Override
    public List<CommentModel> readByNewsId(Long newsId) {
        TypedQuery<CommentModel> typedQuery = entityManager
                .createQuery("SELECT c FROM CommentModel c INNER JOIN c.news n WHERE n.id=:newsId", CommentModel.class)
                .setParameter("newsId", newsId);
        return typedQuery.getResultList();
    }

    @Override
    void updatedEntityFields(CommentModel entity, CommentModel updatedEntity) {
        entity.setName(updatedEntity.getName());
        entity.setNews(updatedEntity.getNews());
    }
}

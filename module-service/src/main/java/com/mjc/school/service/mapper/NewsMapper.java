package com.mjc.school.service.mapper;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@Mapper(componentModel = "spring",uses = {TagMapper.class,
        AuthorMapper.class, CommentMapper.class})
public abstract class  NewsMapper {
 @Autowired
 protected AuthorRepository authorRepository;
 @Autowired
 protected TagRepository tagRepository;

 public abstract List<NewsDtoResponse> modelListToDtoList(List<NewsModel> modelList);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", expression =
            "java(authorRepository.getReference(request.getAuthorId()))")
    @Mapping(target = "tags", expression =
            "java(new ArrayList<>(request.getTagsIds().stream().map(tagId -> tagRepository.getReference(tagId)).toList()))")
    public abstract NewsModel newsFromDtoRequest(NewsDtoRequest request);

    @Mapping(source = "author", target = "authorDto")
    @Mapping(source = "tags", target = "tagsDto")
    @Mapping(source = "comments", target= "commentsDto")
    public abstract NewsDtoResponse newsToDtoResponse(NewsModel model);
}

package com.mjc.school.service.mapper;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = NewsMapper.class)
public interface TagMapper {
    List<TagDtoResponse> modelListToDtoListResponse(List<TagModel> modelList);

    @Mapping(target = "news", ignore = true)
    TagModel tagFromDtoRequest(TagDtoRequest request);

    TagDtoResponse tagToDtoResponse(TagModel tagModel);
}

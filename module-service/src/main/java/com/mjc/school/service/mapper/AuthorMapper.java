package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> modelList);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "news", ignore = true)
    AuthorModel authorFromDtoRequest(AuthorDtoRequest request);

    AuthorDtoResponse authorToDtoResponse(AuthorModel model);

}

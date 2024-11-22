package com.mobilebg.model.mapper;

import com.mobilebg.model.dto.UserRegisterDTO;
import com.mobilebg.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "isActive", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}

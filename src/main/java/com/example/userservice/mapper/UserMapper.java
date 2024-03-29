package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserEntity;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract UserDto voToDto(RequestUser vo);

    @Mapping(target = "id", ignore = true)
    public abstract UserEntity dtoToEntity(UserDto dto);

    public abstract UserDto entityToDto(UserEntity entity);

    public abstract UserDto entityToDto(UserEntity entity, List<ResponseOrder> orders);

    public abstract ResponseUser dtoToVo(UserDto dto);
}

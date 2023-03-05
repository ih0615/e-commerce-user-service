package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserEntity;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "pwd", target = "encryptedPwd", qualifiedByName = "encryptUserPwd")
    public abstract UserDto voToDto(RequestUser vo);

    @Mapping(target = "id", ignore = true)
    public abstract UserEntity dtoToEntity(UserDto dto);

    public abstract UserDto entityToDto(UserEntity entity);

    public abstract ResponseUser dtoToVo(UserDto dto);

    @Named("encryptUserPwd")
    public String encryptUserPwd(String pwd) {
        return passwordEncoder.encode(pwd);
    }
}

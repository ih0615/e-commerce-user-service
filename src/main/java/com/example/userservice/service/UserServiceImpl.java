package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserEntity;
import com.example.userservice.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity user = userMapper.dtoToEntity(userDto);

        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userMapper.entityToDto(userEntity, Collections.emptyList());
    }

    @Override
    public List<UserDto> getUserByAll() {
        final List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
            .map(entity -> userMapper.entityToDto(entity, Collections.emptyList()))
            .collect(Collectors.toList());
    }
}

package com.example.testtaskclearsolutions.dto.mapper;

import com.example.testtaskclearsolutions.dto.request.UserRequestDto;
import com.example.testtaskclearsolutions.dto.response.UserResponseDto;
import com.example.testtaskclearsolutions.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoMapper<User, UserRequestDto, UserResponseDto> {
    @Override
    public User toModel(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setAddress(requestDto.getAddress());
        user.setBirthDate(requestDto.getBirthDate());
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setBirthDate(user.getBirthDate());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        return userResponseDto;
    }
}

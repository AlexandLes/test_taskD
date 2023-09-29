package com.example.testtaskclearsolutions.controller;

import com.example.testtaskclearsolutions.dto.mapper.DtoMapper;
import com.example.testtaskclearsolutions.dto.request.UserRequestDto;
import com.example.testtaskclearsolutions.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.example.testtaskclearsolutions.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.testtaskclearsolutions.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private DtoMapper<User, UserRequestDto, UserResponseDto> userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toModel(userRequestDto))));
    }

    @GetMapping("/date-between")
    public List<UserResponseDto> getAllUsersBetweenDate(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate from,
                                                        @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate to) {
        return userService.searchUsersByBirthDateRange(from, to).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        user.setId(id);
        return userMapper.toDto(userService.update(user));
    }

}

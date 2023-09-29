package com.example.testtaskclearsolutions.service;

import com.example.testtaskclearsolutions.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User save(User user);

    User update(User user);

    void delete(Long id);

    List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to);
}

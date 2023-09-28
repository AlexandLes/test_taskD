package service;

import model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    User findById(Long id);

    User update(Long id, User user);

    void delete(Long id);

    List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to);

    User findByEmail(String email);
}

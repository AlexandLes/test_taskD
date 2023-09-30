package com.example.testtaskclearsolutions.repository;

import com.example.testtaskclearsolutions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> searchUserByBirthDateBetween(LocalDate from, LocalDate to);
}

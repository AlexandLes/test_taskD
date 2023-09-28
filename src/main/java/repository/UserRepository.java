package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> searchUserByBirthDateBetween(LocalDate from, LocalDate to);
    User findUserByEmail(String email);
}

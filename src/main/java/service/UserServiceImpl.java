package service;

import exception.UserNotFoundException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find user by id: " + id));
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> findedUser  = userRepository.findById(id);
        if (findedUser.isPresent()) {
            User userToUpdate = findedUser.get();
            userToUpdate.setAddress(user.getAddress());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            return userRepository.saveAndFlush(userToUpdate);
        } else {
            throw new UserNotFoundException("User with ID " + user.getId() + " not found");
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to) {
        return userRepository.searchUserByBirthDateBetween(from, to);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}

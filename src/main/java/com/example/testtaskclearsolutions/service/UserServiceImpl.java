package com.example.testtaskclearsolutions.service;

import com.example.testtaskclearsolutions.exception.UserAgeException;
import com.example.testtaskclearsolutions.exception.UserNotFoundException;
import com.example.testtaskclearsolutions.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.testtaskclearsolutions.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private int minAge = 18;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        LocalDate now = LocalDate.now();
        if (Period.between(user.getBirthDate(), now).getYears() < minAge){
            throw new UserAgeException("User age has to be greater than 18");
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find user by id: " + id));
    }

    @Override
    public User update(User user) {
        User editedUser = userRepository.findById(user.getId()).orElseThrow(() ->
                new UserNotFoundException("User not found"));
        editedUser.setAddress(user.getAddress());
        editedUser.setEmail(user.getEmail());
        editedUser.setLastName(user.getLastName());
        editedUser.setPhoneNumber(user.getPhoneNumber());
        editedUser.setFirstName(user.getFirstName());
        return  userRepository.saveAndFlush(editedUser);
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

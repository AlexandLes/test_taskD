package com.example.testtaskclearsolutions;

import com.example.testtaskclearsolutions.exception.IncorrectDateException;
import com.example.testtaskclearsolutions.exception.UserAgeException;
import com.example.testtaskclearsolutions.model.User;
import com.example.testtaskclearsolutions.repository.UserRepository;
import com.example.testtaskclearsolutions.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldReturnUserAgeException() {
        userService.setMinAge("18");
        Assertions.assertThrows(UserAgeException.class, () ->
                Mockito.when(userService.save(new User(1L,
                        "aaa@gmail.com",
                        "john",
                        "doe",
                        LocalDate.now().minusYears(17),
                        "address",
                        "+380934581"))).thenThrow(UserAgeException.class));
    }

    @Test
    public void shouldReturnIncorrectDateException() {
        Assertions.assertThrows(IncorrectDateException.class, () ->
                Mockito.when(userService.searchUsersByBirthDateRange(LocalDate.of(1990,1,1),
                                LocalDate.of(1989,1,1)))
                        .thenThrow(UserAgeException.class));
    }

}
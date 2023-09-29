package com.example.testtaskclearsolutions;

import static org.mockito.Mockito.verify;
import com.example.testtaskclearsolutions.dto.request.UserRequestDto;
import com.example.testtaskclearsolutions.model.User;
import com.example.testtaskclearsolutions.service.UserService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldShowAllUsersDatesBetween() {
        List<User> mockUsers = List.of(
                new User(1L,
                        "john.doe@example.com",
                        "John",
                        "Doe",
                        LocalDate.of(1990, 1, 1),
                        "123 Main Street",
                        "+1 123-456-7890"),
                new User(2L,
                        "jane.doe@emple.com",
                        "Jane",
                        "Doe",
                        LocalDate.of(1994, 1, 1),
                        "123 Main Street",
                        "+1 123-456-7820"),
                new User(3L,
                        "paul.doe@emple.com",
                        "Paul",
                        "Doe",
                        LocalDate.of(1987, 1, 1),
                        "123 Main Street",
                        "+1 123-456-7820")
        );
        Mockito.when(userService.searchUsersByBirthDateRange(
                LocalDate.of(1934, 1, 2),
                LocalDate.of(2001, 2, 3)
        )).thenReturn(mockUsers);
        RestAssuredMockMvc.when()
                .get("http://localhost:8080/users/date-between?from=1934-01-02&to=2001-02-03")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[1].firstName", Matchers.equalTo("Jane"))
                .body("[2].lastName", Matchers.equalTo("Doe"));
    }

    @Test
    public void shouldCreateUser() {
        User user = new User("john.doe@example.com",
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "123 Main Street",
                "+1 123-456-7890");
        Mockito.when(userService.save(user))
                .thenReturn(new User(1L,
                        "john.doe@example.com",
                        "John",
                        "Doe",
                        LocalDate.of(1990, 1, 1),
                        "123 Main Street",
                        "+1 123-456-7890"));
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new UserRequestDto(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthDate(),
                        user.getAddress(),
                        user.getPhoneNumber()))
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("email", Matchers.equalTo("john.doe@example.com"))
                .body("firstName", Matchers.equalTo("John"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}",userId ))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(userService, Mockito.times(1)).delete(userId);
    }
}
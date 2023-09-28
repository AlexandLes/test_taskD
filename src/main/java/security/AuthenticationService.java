package security;

import model.User;

public interface AuthenticationService {
    User register(String email, String password);

    User login(String login, String password);
}

package services;

import models.User;
import models.User.Role;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public UserService() {
        // user default: admin / admin123
        users.put("admin",
                new User(1, "admin", "admin123", "admin@mail.com", "Florjete", "Kuka", Role.ADMIN));
    }

    public User authenticate(String username, String rawPassword) {
        User u = users.get(username);
        if (u == null) return null;
        return rawPassword.equals(u.getPassword()) ? u : null;
    }

    public void register(User u) {
        users.put(u.getUsername(), u);
    }
}

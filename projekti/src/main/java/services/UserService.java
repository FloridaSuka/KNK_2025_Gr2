package services;

import models.User;
import models.dto.create.CreateUser;
import models.dto.update.UpdateUser;
import repositories.UserRepository;

public class UserService {
    private static User currentUser;
    private final UserRepository userRepository = new UserRepository();

    // 🔹 Thirrja për regjistrimin e përdoruesit
    public boolean register(CreateUser user) {
        return userRepository.shtoUser(user);
    }

    // 🔹 Thirrja për autentikim
    public User authenticate(String username, String password) {
        User user = userRepository.authenticate(username, password);
        if (user != null) {
            currentUser = user;
        }
        return user;
    }

    public boolean verifyUser(String username, String verificationCode) {
        return userRepository.verifyUser(username, verificationCode);
    }

    // ✅ Përditësimi i fjalëkalimit
    public boolean updatePassword(UpdateUser updateUser) {
        return userRepository.updatePassword(updateUser);
    }

    // ✅ Metoda që mungon
    public static User getCurrentUser() {
        return currentUser;
    }
}

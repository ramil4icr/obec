package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    User saveUser(User user);
    List<User> getActiveUsers();
    User getByUserId(String userId);
}

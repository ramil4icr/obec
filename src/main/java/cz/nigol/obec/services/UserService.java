package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.Path;
import cz.nigol.obec.entities.Role;
import cz.nigol.obec.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    User saveUser(User user);
    List<User> getActiveUsers();
    User getByUserId(String userId);
    List<Role> getAllRoles();
    Role saveRole(Role role);
    void deleteRole(Role role);
    Role getRoleById(long id);
    List<Path> initializePaths(List<String> paths);
    Path getPathById(String id);
    Role saveRole(Role role, List<Path> paths);
}

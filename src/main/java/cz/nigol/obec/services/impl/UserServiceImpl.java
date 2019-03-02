package cz.nigol.obec.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.obec.entities.Path;
import cz.nigol.obec.entities.Role;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.services.UserService;

@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_ALL, User.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> getActiveUsers() {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_ACTIVE, User.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public User getActiveUserByUserId(String userId) {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_ACTIVE_BY_USER_ID, User.class);
        typedQuery.setParameter(User.USER_ID_PARAM, userId);
        List<User> users = typedQuery.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> typedQuery = em.createNamedQuery(Role.GET_ALL, Role.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public Role saveRole(Role role) {
        return em.merge(role);
    }

    @Override
    public void deleteRole(Role role) {
        em.remove(em.merge(role));
    }

    @Override
    public Role getRoleById(long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Path> initializePaths(List<String> paths) {
        return paths.stream()
            .map(this::createPath)
            .collect(Collectors.toList());
    }

    private Path createPath(String path) {
        return em.merge(new Path(path));
    }

    @Override
    public Path getPathById(String id) {
        return em.find(Path.class, id);
    }

    @Override
    public Role saveRole(Role role, List<Path> paths) {
        Role roleInContext = em.merge(role);
        roleInContext.setPaths(new HashSet<>());
        roleInContext.getPaths().addAll(paths);
        return roleInContext;
    }

    @Override
    public User getUserByUserId(String userId) {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.GET_BY_USER_ID, User.class);
        typedQuery.setParameter(User.USER_ID_PARAM, userId);
        List<User> users = typedQuery.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}

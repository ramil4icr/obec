package cz.nigol.obec.beans.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import cz.nigol.obec.entities.Role;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class UsersBean implements Serializable {
    private static final long serialVersionUID = -1174277303661709368L;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private User user;
    private List<User> users;
    private List<Role> roles;
    private String password;

    @PostConstruct
    public void init() {
        users = userService.getAllUsers();
        roles = userService.getAllRoles();
    }

    public void newUser() {
        user = new User();
        if (!roles.isEmpty()) {
            user.setRole(roles.get(0));
        }
        users.add(user);
    }

    public void save() {
        if (password != null && !"".equals(password)) {
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        }
        userService.saveUser(user);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Uživatel '" +
                    user.getUserId() + "' byl uložen."));
        user = null;
        init();
    }

    public void cancelEdit() {
        user = null;
        init();
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

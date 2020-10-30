package cz.nigol.obec.beans;

import java.io.*;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.*;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.qualifiers.*;
import cz.nigol.obec.services.*;

@Named
@ViewScoped
public class PasswordResetBean implements Serializable {
    private static final long serialVersionUID = 5058918096082190355L;
    @Inject
    @CurrentSettings
    private Settings settings;
    @Inject
    private FacesContext facesContext;
    @Inject
    private UserService userService;
    private String email;
    private boolean sent;
    private String token;
    private boolean validToken;
    private User user;
    private boolean changed;
    private String password1;
    private String password2;

    @PostConstruct
    public void init() {
    }

    public void onLoad() throws IOException {
        user = userService.getUserByToken(token);
        if (user != null) {
            validToken = user.getToken().equals(token);
            user.setToken("");
            userService.saveUser(user);
        }
    }

    public void send() {
        userService.sendPasswordLinkByEmail(email);
        sent = true;
    }

    public void change() {
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValidToken() {
        return validToken;
    }

    public void setValidToken(boolean validToken) {
        this.validToken = validToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

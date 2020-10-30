package cz.nigol.obec.beans;

import java.io.*;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.*;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.qualifiers.*;
import cz.nigol.obec.services.*;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;

@Named
@SessionScoped
public class NewPasswordBean implements Serializable {
    private static final long serialVersionUID = 5058918096082190355L;
    @Inject
    @CurrentSettings
    private Settings settings;
    @Inject
    private FacesContext facesContext;
    @Inject
    private UserService userService;
    private String token;
    private boolean validToken;
    private User user;
    private boolean changed;
    private String password1;
    private String password2;

    public void onLoad() {
        user = userService.getUserByToken(token);
        if (user != null) {
            validToken = user.getToken().equals(token);
            user.setToken(null);
            userService.saveUser(user);
        }
    }

    public void change() {
        boolean passValid = password1 != null && password1.equals(password2);
        if (passValid) {
            user.setPassword(BCrypt.hashpw(password1, BCrypt.gensalt()));
            userService.saveUser(user);
            changed = true;
        } else {
            facesContext.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                        "Hesla nesouhlasí."));
        }
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

}

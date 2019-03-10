package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import cz.nigol.obec.entities.User;
import cz.nigol.obec.services.UserService;

@Named
@RequestScoped
public class LoginBean {
    @Inject
    private SessionBean sessionBean;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private String loginName;
    private String password;

    public String login() {
        String result;
        User user = userService.getActiveUserByUserId(loginName);
        if (user != null && user.isActive() && BCrypt.checkpw(password, user.getPassword())) {
            sessionBean.setUser(user);
            result = "/uzivatel/prehled.xhtml?faces-redirect=true";
        } else {
            sessionBean.setUser(null);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "",  "Nesprávné přihlašovací údaje."));
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            result = "/prihlaseni.xhtml?faces-redirect=true";
        }
        return result;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

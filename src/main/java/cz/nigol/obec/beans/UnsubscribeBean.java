package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.*;
import javax.inject.*;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Named
@RequestScoped
public class UnsubscribeBean {
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private long r;
    private User user;

    public void onLoad() {
        if (r != 0) {
            user = userService.getUserById(r);
            userService.unsubscribeAnnouncements(r);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getR() {
        return r;
    }

    public void setR(long r) {
        this.r = r;
    }
}
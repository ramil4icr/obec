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

    @PostConstruct
    public void init() {
    }

    public void onLoad() throws IOException {
    }

    public void send() {
        userService.sendPasswordLinkByEmail(email);
        sent = true;
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

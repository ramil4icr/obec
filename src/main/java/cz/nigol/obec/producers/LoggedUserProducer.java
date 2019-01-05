package cz.nigol.obec.producers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.beans.SessionBean;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;

@Named
@ApplicationScoped
class LoggedUserProducer {
    @Inject
    private SessionBean sessionBean;

    @Produces
    @LoggedUser
    @RequestScoped
    public User loggedUser() {
        return sessionBean.getUser();
    }
}

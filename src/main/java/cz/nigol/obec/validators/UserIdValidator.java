package cz.nigol.obec.validators;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.beans.admin.UsersBean;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.services.UserService;

@Named
@ApplicationScoped
public class UserIdValidator implements Validator {
    @Inject
    private UserService userService;
    @Inject
    private UsersBean usersBean;

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        String userId = (String) arg2;
        User user = userService.getUserByUserId(userId); 
        if (user != null && usersBean.getUser().getId() != user.getId()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Toto uživatelské id již existuje.", null));
        }
    }
}

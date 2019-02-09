package cz.nigol.obec.dev;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import cz.nigol.obec.entities.User;
import cz.nigol.obec.services.UserService;

@Named
@RequestScoped
public class PrepareDevData {
    @Inject
    private UserService userService;

    public void createData() {
	User user = new User();
	user.setUserId("aaa");
	user.setFullName("Josef Test");
	user.setPassword(BCrypt.hashpw("aaa", BCrypt.gensalt()));
	user.setActive(true);
	userService.saveUser(user);
    }
}

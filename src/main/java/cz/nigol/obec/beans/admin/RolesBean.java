package cz.nigol.obec.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Path;
import cz.nigol.obec.entities.Role;
import cz.nigol.obec.qualifiers.SecuredPaths;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class RolesBean implements Serializable {
    private static final long serialVersionUID = -1040736084798231068L;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    @Inject
    @SecuredPaths
    private List<Path> paths;
    private List<Path> selectedPaths;
    private List<Role> roles;
    private Role role;

    @PostConstruct
    public void init() {
	roles = userService.getAllRoles();
    }

    public void newRole() {
	role = new Role();
	roles.add(role);
	selectedPaths = new ArrayList<>();
    }

    public void delete() {
	userService.deleteRole(role);
	role = null;
	init();
    }

    public void save() {
	userService.saveRole(role, selectedPaths);
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Role '" +
						       role.getName() + "' byla uložena."));
	role = null;
	init();
    }

    public void cancelEdit() {
	role = null;
	init();
    }

    public void onRoleSelect() {
	selectedPaths = new ArrayList<>(role.getPaths());
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
     * @return the paths
     */
    public List<Path> getPaths() {
	return paths;
    }

    /**
     * @param paths the paths to set
     */
    public void setPaths(List<Path> paths) {
	this.paths = paths;
    }

    /**
     * @return the selectedPaths
     */
    public List<Path> getSelectedPaths() {
	return selectedPaths;
    }

    /**
     * @param selectedPaths the selectedPaths to set
     */
    public void setSelectedPaths(List<Path> selectedPaths) {
	this.selectedPaths = selectedPaths;
    }

    /**
     * @return the role
     */
    public Role getRole() {
	return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
	this.role = role;
    }
}

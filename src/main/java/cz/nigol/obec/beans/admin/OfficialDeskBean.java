package cz.nigol.obec.beans.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;

import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.OfficialDeskService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class OfficialDeskBean implements Serializable {
    private static final long serialVersionUID = -1305585869888155765L;
    @Inject
    private OfficialDeskService officialDeskService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private List<DeskItem> deskItems;
    private DeskItem deskItem;

    @PostConstruct
    public void init() {
	loadDeskItems();
	user = userService.getUserById(user.getId());
    }

    private void loadDeskItems() {
	deskItems = officialDeskService.getAllDeskItems();
    }

    public void newDeskItem() {
	deskItem = new DeskItem();
	deskItem.setCreatedAt(new Date());
	deskItem.setCreatedBy(user);
	deskItems.add(deskItem);
    }

    public void delete() {
	officialDeskService.deleteDeskItem(deskItem);
	deskItem = null;
	loadDeskItems();
    }

    public void save() {
	officialDeskService.saveDeskItem(deskItem);
	deskItem = null;
	loadDeskItems();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dokument byl uložen."));
    }

    public void cancelEdit() {
	deskItem = null;
	loadDeskItems();
    }

    /**
     * @return the deskItems
     */
    public List<DeskItem> getDeskItems() {
	return deskItems;
    }

    /**
     * @param deskItems the deskItems to set
     */
    public void setDeskItems(List<DeskItem> deskItems) {
	this.deskItems = deskItems;
    }

    /**
     * @return the deskItem
     */
    public DeskItem getDeskItem() {
	return deskItem;
    }

    /**
     * @param deskItem the deskItem to set
     */
    public void setDeskItem(DeskItem deskItem) {
	this.deskItem = deskItem;
    }
}

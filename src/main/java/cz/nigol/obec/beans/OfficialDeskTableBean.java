package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.services.OfficialDeskService;

@Named
@ViewScoped
public class OfficialDeskTableBean implements Serializable {
    private static final long serialVersionUID = -3038448109293217757L;
    @Inject
    private OfficialDeskService officialDeskService;
    private List<DeskItem> deskItems;

    @PostConstruct
    public void init() {
    	deskItems = officialDeskService.getAllValidDeskItems(new Date());
    }

    public boolean activeItem(DeskItem deskItem) {
	Date today = new Date();
	Date to = deskItem.getActiveTo();
	to = to == null ? new Date(today.getTime() + 24 * 60 * 60 * 1000) : to;
	return today.compareTo(deskItem.getActiveFrom()) > 0 &&
	    today.compareTo(to) < 0;
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
    
}

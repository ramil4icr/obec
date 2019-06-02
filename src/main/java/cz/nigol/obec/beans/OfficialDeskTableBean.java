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
    private static final String ACTIVE = "Active";
    private static final String ALL = "All";
    @Inject
    private OfficialDeskService officialDeskService;
    private List<DeskItem> deskItems;
    private String display = ACTIVE;

    @PostConstruct
    public void init() {
        loadData();
    }

    public void loadData() {
        if (ALL.equals(display)) {
            deskItems = officialDeskService.getAllValidDeskItems(new Date());
        } else {
            deskItems = officialDeskService.getActiveDeskItemsFor(new Date());
        }
    }

    public boolean activeItem(DeskItem deskItem) {
        Date today = new Date();
        Date to = deskItem.getActiveTo();
        to = to == null ? new Date(today.getTime() + 24 * 60 * 60 * 1000) : to;
        return today.compareTo(deskItem.getActiveFrom()) > 0 &&
            today.compareTo(to) < 0;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return ACTIVE;
    }

    /**
     * @return the all
     */
    public String getAll() {
        return ALL;
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
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }

}

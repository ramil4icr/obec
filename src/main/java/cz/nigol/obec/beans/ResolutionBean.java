package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.enums.OfficialDeskCategory;
import cz.nigol.obec.services.OfficialDeskService;

@Named
@ViewScoped
public class ResolutionBean implements Serializable {
    private static final long serialVersionUID = 6087026979515238258L;
    @Inject
    private OfficialDeskService officialDeskService;
    private List<DeskItem> deskItems;

    @PostConstruct
    public void init() {
        deskItems = officialDeskService.getDeskItemsByCategory(OfficialDeskCategory.USNESENI, new Date());
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

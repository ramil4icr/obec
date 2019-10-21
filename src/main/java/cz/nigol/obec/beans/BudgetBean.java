package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.entities.BudgetFulfillment;
import cz.nigol.obec.enums.OfficialDeskCategory;
import cz.nigol.obec.services.OfficialDeskService;
import cz.nigol.obec.services.BudgetService;

@Named
@ViewScoped
public class BudgetBean implements Serializable {
    private static final long serialVersionUID = 6087026979515238258L;
    @Inject
    private OfficialDeskService officialDeskService;
    @Inject
    private BudgetService budgetService;
    private List<DeskItem> deskItems;
    private BudgetFulfillment budgetFulfillment;

    @PostConstruct
    public void init() {
        deskItems = officialDeskService.getDeskItemsByCategory(OfficialDeskCategory.ROZPOCET);
    }

    public BudgetFulfillment getBudgetFulfillment() {
        return budgetFulfillment;
    }

    public void setBudgetFulfillment(BudgetFulfillment budgetFulfillment) {
        this.budgetFulfillment = budgetFulfillment;
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

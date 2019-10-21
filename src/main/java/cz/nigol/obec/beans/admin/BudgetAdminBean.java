package cz.nigol.obec.beans.admin;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.BudgetFulfillment;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.BudgetService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class BudgetAdminBean implements Serializable {
    private static final long serialVersionUID = 5831093403743197357L;
    @Inject
    private BudgetService budgetService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private BudgetFulfillment budgetFulfillment;

    @PostConstruct
    public void init() {
        user = userService.getUserById(user.getId());
        prepareBudgetFulfillment();
    }

    private void prepareBudgetFulfillment() {
        BudgetFulfillment bf = budgetService.getLatestBudgetFulfillment();
        budgetFulfillment = new BudgetFulfillment();
        budgetFulfillment.setCreatedAt(new Date());
        budgetFulfillment.setCreatedBy(user);
        if (bf == null) {
            budgetFulfillment.setIncomePlan(BigDecimal.ZERO);
            budgetFulfillment.setExpenditurePlan(BigDecimal.ZERO);
        } else {
            budgetFulfillment.setIncomePlan(bf.getIncomePlan());
            budgetFulfillment.setExpenditurePlan(bf.getExpenditurePlan());
        }
    }

    public void saveBudgetFulFillment() {
        budgetService.saveBudgetFulfillment(budgetFulfillment);
        prepareBudgetFulfillment();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Data byla uložena."));
    }

    public BudgetFulfillment getBudgetFulfillment() {
        return budgetFulfillment;
    }

    public void setBudgetFulfillment(BudgetFulfillment bf) {
        this.budgetFulfillment = bf;
    }
}

package cz.nigol.obec.services;

import cz.nigol.obec.entities.BudgetFulfillment;

public interface BudgetService {
    BudgetFulfillment getLatestBudgetFulfillment();
    BudgetFulfillment saveBudgetFulfillment(BudgetFulfillment budgetFulfillment);
}

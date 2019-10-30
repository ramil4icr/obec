package cz.nigol.obec.services.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.obec.entities.BudgetFulfillment;
import cz.nigol.obec.services.BudgetService;

@Stateless
public class BudgetServiceImpl implements BudgetService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public BudgetFulfillment getLatestBudgetFulfillment() {
        TypedQuery<BudgetFulfillment> typedQuery = em.createNamedQuery(BudgetFulfillment.GET_ALL, BudgetFulfillment.class);
        typedQuery.setMaxResults(1);
        List<BudgetFulfillment> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public BudgetFulfillment saveBudgetFulfillment(BudgetFulfillment budgetFulfillment) {
        return em.merge(budgetFulfillment);
    }
}

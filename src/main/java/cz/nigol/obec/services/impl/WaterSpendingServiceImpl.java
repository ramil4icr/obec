package cz.nigol.obec.services.impl;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Stateless
public class WaterSpendingServiceImpl implements WaterSpendingService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public List<WaterSpending> getAllWaterSpendings() {
        TypedQuery<WaterSpending> typedQuery = em.createNamedQuery(WaterSpending.GET_ALL, WaterSpending.class);
        return typedQuery.getResultList();
    }

    @Override
    public void saveWaterSpending(WaterSpending waterSpending) {
        em.merge(waterSpending);
    }
}
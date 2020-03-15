package cz.nigol.obec.services.impl;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Stateless
public class HelpOfferServiceImpl implements HelpOfferService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public List<HelpOffer> getAllHelpOffers() {
        TypedQuery<HelpOffer> typedQuery = em.createNamedQuery(HelpOffer.GET_ALL, HelpOffer.class);
        return typedQuery.getResultList();
    }

    @Override
    public void saveHelpOffer(HelpOffer helpOffer) {
        em.merge(helpOffer);
    }
}
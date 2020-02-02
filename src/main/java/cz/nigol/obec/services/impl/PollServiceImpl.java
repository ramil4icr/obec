package cz.nigol.obec.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.PollService; 

@Stateless
public class PollServiceImpl implements PollService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public Poll findById(long id) {
        return em.find(Poll.class, id);
    }

    @Override
    public Poll getActivePoll() {
        TypedQuery<Poll> typedQuery = em.createNamedQuery(Poll.GET_ACTIVE, Poll.class);
        List<Poll> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Poll savePoll(Poll poll) {
        return em.merge(poll);
    }

    @Override
    public List<Poll> getAllPolls() {
        TypedQuery<Poll> typedQuery = em.createNamedQuery(Poll.GET_ALL, Poll.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public void savePollAnswer(PollAnswer pollAnswer) {
        em.merge(pollAnswer);
    }
}

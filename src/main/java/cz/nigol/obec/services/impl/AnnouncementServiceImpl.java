package cz.nigol.obec.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.services.AnnouncementService;

@Stateless
public class AnnouncementServiceImpl implements AnnouncementService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public List<Announcement> getAll() {
	TypedQuery<Announcement> typedQuery = em.createNamedQuery(Announcement.GET_ALL, Announcement.class);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public Announcement save(Announcement announcement) {
	return em.merge(announcement);
    }

    @Override
    public Announcement getById(long id) {
	return em.find(Announcement.class, id);
    }

    @Override
    public void delete(Announcement announcement) {
	em.remove(announcement);
    }
    
}

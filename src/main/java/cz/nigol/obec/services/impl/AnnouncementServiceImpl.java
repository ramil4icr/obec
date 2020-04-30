package cz.nigol.obec.services.impl;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.entities.interfaces.*;
import cz.nigol.obec.services.*;
import javax.inject.*;
import cz.nigol.obec.config.*;

@Stateless
public class AnnouncementServiceImpl implements AnnouncementService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;
    @Inject
    private UserService userService;
    @Inject
    private MailService mailService;

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
        em.remove(em.merge(announcement));
    }

    @Override
    public List<Announcement> getLastFive() {
        TypedQuery<Announcement> typedQuery = em.createNamedQuery(Announcement.GET_ALL, Announcement.class);
        typedQuery.setMaxResults(5);
        return new ArrayList<>(typedQuery.getResultList()); 
    }

    @Override
    public List<RssItem> getAllRss() {
        TypedQuery<RssItem> typedQuery = em.createNamedQuery(Announcement.GET_ALL, RssItem.class);
        return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public List<Announcement> getLastTen() {
        TypedQuery<Announcement> typedQuery = em.createNamedQuery(Announcement.GET_ALL, Announcement.class);
        typedQuery.setMaxResults(10);
        return new ArrayList<>(typedQuery.getResultList()); 
    }

    @Override
    @Asynchronous
    public void sendAnnouncementByEmail(Announcement announcement) {
        String body = Templates.ANNOUNCEMENT
            .replaceAll("VARIABLE1", announcement.getBody());
        userService.getAnnouncementSubscribers().stream()
            .forEach(u -> {
                mailService.sendEmail(u.getEmail(), Templates.ANNOUNCEMENT_SUBJ, body.replaceAll("VARIABLE2", "" + u.getId()));
            });
    }
}

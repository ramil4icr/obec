package cz.nigol.obec.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.nigol.obec.entities.News;
import cz.nigol.obec.services.NewsService;

@Stateless
public class NewsServiceImpl implements NewsService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public News save(News news) {
	return em.merge(news);
    }

    @Override
    public List<News> getAll() {
	TypedQuery<News> typedQuery = em.createNamedQuery(News.GET_ALL, News.class);
	return new ArrayList<>(typedQuery.getResultList());
    }

    @Override
    public News getById(long id) {
	return em.find(News.class, id);
    }

    @Override
    public void delete(News news) {
	em.remove(em.merge(news));
    }
}

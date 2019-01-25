package cz.nigol.obec.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.News;
import cz.nigol.obec.services.NewsService;


@Named
@RequestScoped
public class IndexBean {
    @Inject
    private NewsService newsService;
    private List<News> newsList;

    @PostConstruct
    public void init() {
	newsList = newsService.getFeatured();
    }

    /**
     * @return the newsList
     */
    public List<News> getNewsList() {
	return newsList;
    }

    /**
     * @param newsList the newsList to set
     */
    public void setNewsList(List<News> newsList) {
	this.newsList = newsList;
    }

}

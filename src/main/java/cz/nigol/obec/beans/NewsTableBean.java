package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Article;
import cz.nigol.obec.entities.News;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.ArticleService;
import cz.nigol.obec.services.NewsService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class NewsTableBean implements Serializable {
    private static final long serialVersionUID = 3124927174451007316L;
    @Inject
    private NewsService newsService;
    private List<News> newsList;

    @PostConstruct
    public void init() {
	newsList = newsService.getAll();
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

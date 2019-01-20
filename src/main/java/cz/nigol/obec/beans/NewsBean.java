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

@Named
@ViewScoped
public class NewsBean implements Serializable {
    private static final long serialVersionUID = 3124927174451007316L;
    @Inject
    private NewsService newsService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private ArticleService articleService;
    @Inject
    private FacesContext facesContext;
    private List<News> newsList;
    private News news;
    private String body;

    @PostConstruct
    public void init() {
	newsList = newsService.getAll();
    }

    public void onNewsSelect() {
    }

    public void newNews() {
	news = new News();
	news.setArticle(new Article());
	body = null;
    }

    public void save() {
	news.setChangedAt(new Date());
	Article article = news.getArticle();
	article.setChangedAt(news.getChangedAt());
	article.setId(article.getId() == null ? news.getLabel() : article.getId());
	article.setChangedBy(user);
	article.setBody(body);
	newsService.save(news);
	String label = news.getLabel();
	news = null;
	body = null;
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Aktualita '" +
						       label + "' byla uložena."));
    }

    public void cancelEdit() {
	news = null;
	body = null;
	init();
    }

    public void delete() {
	newsService.delete(news);
	news = null;
	body = null;
	init();
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

    /**
     * @return the news
     */
    public News getNews() {
	return news;
    }

    /**
     * @param news the news to set
     */
    public void setNews(News news) {
	this.news = news;
    }

    /**
     * @return the body
     */
    public String getBody() {
	return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
	this.body = body;
    }
}

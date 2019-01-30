package cz.nigol.obec.beans.admin;

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
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.ArticleService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class ArticlesBean implements Serializable {
    private static final long serialVersionUID = -2626968175893152327L;
    @Inject
    private ArticleService articleService;
    @Inject
    private UserService userService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private FacesContext facesContext;
    private List<Article> articles;
    private Article article;
    private String body;
    private boolean editSource;
    private static final String NEW_ID = "článek";

    @PostConstruct
    public void init() {
	articles = articleService.getAllArticles();
	user = userService.getUserById(user.getId());
    }

    public void newArticle() {
	article = new Article();
	body = null;
	article.setId(NEW_ID);
	articles.add(article);
    }

    public void delete() {
	articleService.deleteArticle(article);
	article = null;
	body = null;
	init();
    }

    public void save() {
	article.setChangedAt(new Date());
	article.setChangedBy(user);
	articleService.saveArticle(article, body);
	String id = article.getId();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Článek '" +
						       id + "' byl uložen."));
    }

    public void onArticleSelect() {
	article = articleService.loadArticleBody(article);
	body = article.getBody();
    }

    public void cancelEdit() {
	article = null;
	body = null;
	init();
    }

    /**
     * @return the articles
     */
    public List<Article> getArticles() {
	return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
	this.articles = articles;
    }

    /**
     * @return the article
     */
    public Article getArticle() {
	return article;
    }

    /**
     * @param article the article to set
     */
    public void setArticle(Article article) {
	this.article = article;
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

    /**
     * @return the editSource
     */
    public boolean isEditSource() {
	return editSource;
    }

    /**
     * @param editSource the editSource to set
     */
    public void setEditSource(boolean editSource) {
	this.editSource = editSource;
    }
}

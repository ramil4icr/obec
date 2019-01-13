package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import cz.nigol.obec.entities.Article;

@Named
@ViewScoped
public class ArticlesBean implements Serializable {
    private static final long serialVersionUID = -2626968175893152327L;
    private List<Article> articles;
    private Article article;

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
}

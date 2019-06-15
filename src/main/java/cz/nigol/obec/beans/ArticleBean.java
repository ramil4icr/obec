package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Article;
import cz.nigol.obec.services.ArticleService;

@Named
@RequestScoped
public class ArticleBean {
    @Inject
    private ArticleService articleService;
    private Article article;
    private String articleId;

    public void onLoad() {
        loadArticle(articleId);
    }

    public void loadArticle(String id) {
        article = articleService.getArticleById(id);
        if (article != null) {
            article = articleService.loadArticleBody(article);
        }
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
     * @return the articleId
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId the articleId to set
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}

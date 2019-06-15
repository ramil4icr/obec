package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.Article;
import cz.nigol.obec.entities.User;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(String id);
    Article saveArticle(Article article, String body);
    Article loadArticleBody(Article article);
    List<Article> getArticlesByUser(User user);
    void deleteArticle(Article article);
    List<Article> getArticlesByPattern(String pattern);
}

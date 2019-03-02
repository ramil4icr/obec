package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.News;
import cz.nigol.obec.services.ArticleService;
import cz.nigol.obec.services.NewsService;

@Named
@RequestScoped
public class NewsDetailBean {
    @Inject
    private NewsService newsService;
    @Inject
    private ArticleService articleService;
    private News news;
    private long id;

    public void onLoad() {
        news = newsService.getById(id);
        if (news != null) {
            news.setArticle(articleService.loadArticleBody(news.getArticle()));
        }
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
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}

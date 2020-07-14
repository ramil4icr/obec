package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.ArticleService;

@Named
@RequestScoped
public class MenuBean {
    private static final String MAIN_MENU = "navigace";
    private static final String USER_MENU = "navigace-";
    private static final String BAR_MENU = "bar-navigace";
    @Inject
    private ArticleService articleService;
    @Inject
    private SessionBean sessionBean;
    private Article mainMenu;
    private Article userMenu;
    private Article barMenu;

    @PostConstruct
    public void init() {
        User user = sessionBean.getUser();
        mainMenu = loadArticle(MAIN_MENU, mainMenu);
        if (user != null) {
            userMenu = loadArticle(USER_MENU + user.getRole().getName(), userMenu);
        }
        barMenu = loadArticle(BAR_MENU, barMenu);
    }

    private Article loadArticle(String id, Article article) {
        article = articleService.getArticleById(id);
        if (article != null) {
            article = articleService.loadArticleBody(article);
        }
        return article;
    }

    public Article getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(Article mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Article getUserMenu() {
        return userMenu;
    }

    public void setUserMenu() {
        this.userMenu = userMenu;
    }

    public Article getBarMenu() {
        return barMenu;
    }

    public void setBarMenu(Article barMenu) {
        this.barMenu = barMenu;
    }
}

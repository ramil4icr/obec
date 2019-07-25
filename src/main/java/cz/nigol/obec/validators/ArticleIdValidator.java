package cz.nigol.obec.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.beans.admin.ArticlesBean;
import cz.nigol.obec.services.ArticleService;

@Named
@RequestScoped
public class ArticleIdValidator implements Validator {
    @Inject
    private ArticleService articleService;
    @Inject
    private ArticlesBean articlesBean;

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        String label = (String) arg2;
        if (ArticlesBean.NEW_ID.equals(articlesBean.getArticle().getId()) && 
                articleService.getArticleById(label) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Článek s tímto ID již existuje.", null));
                }
    }
}

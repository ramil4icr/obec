package cz.nigol.obec.validators;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.services.ArticleService;

@Named
@ApplicationScoped
public class ArticleIdValidator implements Validator {
    @Inject
	private ArticleService articleService;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	    String id = (String) arg2;
	    if (articleService.getArticleById(id) != null) {
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
							      "Článek s tímto jménem již existuje.", null));
	    }
	}
}

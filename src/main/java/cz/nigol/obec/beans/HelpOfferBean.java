package cz.nigol.obec.beans;

import java.io.*;
import java.util.*;
import javax.inject.*;
import javax.faces.view.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Named
@ViewScoped
public class HelpOfferBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private HelpOfferService helpOfferService;
    @Inject
    private FacesContext facesContext;
    private HelpOffer helpOffer;
    private boolean agreed;

    @PostConstruct
    public void init() {
        agreed = false;
        helpOffer = new HelpOffer();
    }

    public void save() {
        helpOfferService.saveHelpOffer(helpOffer);
        init();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Vaše nabídka byla uložena. Děkujeme!"));
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public HelpOffer getHelpOffer() {
        return helpOffer;
    }

    public void setHelpOffer(HelpOffer helpOffer) {
        this.helpOffer = helpOffer;
    }
}
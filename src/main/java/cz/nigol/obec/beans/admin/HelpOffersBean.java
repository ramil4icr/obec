package cz.nigol.obec.beans.admin;

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
public class HelpOffersBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private HelpOfferService helpOfferService;
    @Inject
    private FacesContext facesContext;
    private List<HelpOffer> helpOffers;

    @PostConstruct
    public void init() {
        helpOffers = helpOfferService.getAllHelpOffers();
    }

    public List<HelpOffer> getHelpOffers() {
        return helpOffers;
    }

    public void setHelpOffers(List<HelpOffer> helpOffers) {
        this.helpOffers = helpOffers;
    }
}
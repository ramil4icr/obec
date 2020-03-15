package cz.nigol.obec.services;

import java.util.*;
import cz.nigol.obec.entities.*;

public interface HelpOfferService {
    List<HelpOffer> getAllHelpOffers();
    void saveHelpOffer(HelpOffer helpOffer);
}
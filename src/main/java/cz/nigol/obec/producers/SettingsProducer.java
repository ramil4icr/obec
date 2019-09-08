package cz.nigol.obec.producers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.beans.SessionBean;
import cz.nigol.obec.entities.Settings;
import cz.nigol.obec.qualifiers.CurrentSettings;

@Named
@ApplicationScoped
class SettingsProducer {
    @Inject
    private SessionBean sessionBean;

    @Produces
    @CurrentSettings
    @RequestScoped
    public Settings settings() {
        return sessionBean.getSettings();
    }
}

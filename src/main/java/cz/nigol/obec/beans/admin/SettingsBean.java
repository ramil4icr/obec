package cz.nigol.obec.beans.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Settings;
import cz.nigol.obec.services.SettingsService;

@Named
@ViewScoped
public class SettingsBean implements Serializable {
    private static final long serialVersionUID = 5903535557351745485L;
    public static final String ID = "default";
    @Inject
    private SettingsService settingsService;
    @Inject
    private FacesContext facesContext;
    private Settings settings;

    @PostConstruct
    public void init() {
        settings = settingsService.findById(ID);
        if (settings == null) {
            settings = new Settings();
            settings.setId(ID);
        }
    }

    public void save() {
        settingsService.save(settings);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", 
                    "Nastavení bylo uloženo"));
        init();
    }

    /**
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @param settings the settings to set
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}

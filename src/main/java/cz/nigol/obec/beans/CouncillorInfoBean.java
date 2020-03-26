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
import java.util.stream.*;
import cz.nigol.obec.qualifiers.*;
import cz.nigol.obec.enums.*;

@Named
@ViewScoped
public class CouncillorInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private FacesContext facesContext;
    @Inject
    private OfficialDeskService officialDeskService;
    private List<DeskItem> announcements;

    @PostConstruct
    public void init() {
        announcements = officialDeskService.getDeskItemsByCategory(OfficialDeskCategory.VYHLASKY, new Date());
    }

    public List<DeskItem> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<DeskItem> announcements) {
        this.announcements = announcements;
    }
}
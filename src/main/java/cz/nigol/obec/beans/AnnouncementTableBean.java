package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.services.AnnouncementService;

@Named
@ViewScoped
public class AnnouncementTableBean implements Serializable {
    private static final long serialVersionUID = 5058918096082190355L;
    @Inject
    private AnnouncementService announcementService;
    private List<Announcement> announcements;

    @PostConstruct
    public void init() {
        announcements = announcementService.getAll();
    }

    /**
     * @return the announcements
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * @param announcements the announcements to set
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}

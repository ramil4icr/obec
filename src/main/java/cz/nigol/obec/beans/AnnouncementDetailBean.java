package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.services.AnnouncementService;

@Named
@RequestScoped
public class AnnouncementDetailBean {
    @Inject
    private AnnouncementService announcementService;
    private long id;
    private Announcement announcement;

    public void onLoad() {
        announcement = announcementService.getById(id);
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the announcement
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * @param announcement the announcement to set
     */
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }
}

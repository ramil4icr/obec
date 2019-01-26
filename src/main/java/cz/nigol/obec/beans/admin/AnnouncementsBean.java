package cz.nigol.obec.beans.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.AnnouncementService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class AnnouncementsBean implements Serializable {
    private static final long serialVersionUID = -3470793101360663791L;
    @Inject
    private AnnouncementService announcementService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private List<Announcement> announcements;
    private Announcement announcement;

    @PostConstruct
    public void init() {
	user = userService.getUserById(user.getId());
	loadAll();
    }

    private void loadAll() {
	announcements = announcementService.getAll();
    }

    public void newAnnouncement() {
	announcement = new Announcement();
	announcement.setCreatedAt(new Date());
	announcement.setCreatedBy(user);
	announcements.add(announcement);
    }

    public void delete() {
	announcementService.delete(announcement);
	announcement = null;
	loadAll();
    }

    public void save() {
	announcementService.save(announcement);
	announcement = null;
	loadAll();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hlášení bylo uloženo."));
    }

    public void cancel() {
	announcement = null;
	loadAll();
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

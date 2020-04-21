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
import cz.nigol.obec.qualifiers.*;

@Named
@ViewScoped
public class UserOverviewBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private FacesContext facesContext;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private NewsService newsService;
    @Inject
    private AnnouncementService announcementService;
    @Inject
    private OfficialDeskService officialDeskService;
    @Inject
    private EventsService eventsService;
    @Inject
    private UserService userService;
    private List<News> newsList;
    private Announcement announcement;

    @PostConstruct
    public void init() {
        user = userService.getUserById(user.getId());
        newsList = newsService.getFeatured();
        List<Announcement> announcements = announcementService.getLastTen();
        if (!announcements.isEmpty()) {
            announcement = announcements.get(0);
        }
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
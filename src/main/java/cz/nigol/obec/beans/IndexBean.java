package cz.nigol.obec.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.entities.Event;
import cz.nigol.obec.entities.News;
import cz.nigol.obec.services.AnnouncementService;
import cz.nigol.obec.services.EventsService;
import cz.nigol.obec.services.NewsService;
import cz.nigol.obec.services.OfficialDeskService;


@Named
@RequestScoped
public class IndexBean {
    @Inject
    private NewsService newsService;
    @Inject
    private AnnouncementService announcementService;
    @Inject
    private OfficialDeskService officialDeskService;
    @Inject
    private EventsService eventsService;
    private static final int NUMBER_OF_PHOTOS = 2;
    private List<News> newsList;
    private List<Announcement> announcements;
    private List<DeskItem> deskItems;
    private List<Event> events;
    private String nameOfPhoto;

    @PostConstruct
    public void init() {
        newsList = newsService.getFeatured();
        announcements = announcementService.getLastFive();
        deskItems = officialDeskService.getTenActiveDeskItemsFor(new Date());
        events = eventsService.getValidToDate(new Date());
        nameOfPhoto = preparePhoto(NUMBER_OF_PHOTOS);
    }

    private String preparePhoto(int numOfPhotos) {
        Date date = new Date();
        long suffix = date.getTime() % numOfPhotos;
        return "index" + suffix + ".jpeg";
    }

    /**
     * @return the newsList
     */
    public List<News> getNewsList() {
        return newsList;
    }

    /**
     * @param newsList the newsList to set
     */
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
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
     * @return the deskItems
     */
    public List<DeskItem> getDeskItems() {
        return deskItems;
    }

    /**
     * @param deskItems the deskItems to set
     */
    public void setDeskItems(List<DeskItem> deskItems) {
        this.deskItems = deskItems;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * @return the nameOfPhoto
     */
    public String getNameOfPhoto() {
        return nameOfPhoto;
    }

    /**
     * @param nameOfPhoto the nameOfPhoto to set
     */
    public void setNameOfPhoto(String nameOfPhoto) {
        this.nameOfPhoto = nameOfPhoto;
    }

}

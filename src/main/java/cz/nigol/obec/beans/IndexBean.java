package cz.nigol.obec.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Announcement;
import cz.nigol.obec.entities.News;
import cz.nigol.obec.services.AnnouncementService;
import cz.nigol.obec.services.NewsService;


@Named
@RequestScoped
public class IndexBean {
    @Inject
    private NewsService newsService;
    @Inject
    private AnnouncementService announcementService;
    private List<News> newsList;
    private List<Announcement> announcements;

    @PostConstruct
    public void init() {
	newsList = newsService.getFeatured();
	announcements = announcementService.getLastFive();
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

}

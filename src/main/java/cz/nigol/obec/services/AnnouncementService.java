package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.Announcement;

public interface AnnouncementService {
    List<Announcement> getAll();
    Announcement save(Announcement announcement);
    Announcement getById(long id);
    void delete(Announcement announcement);
}

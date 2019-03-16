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

import cz.nigol.obec.entities.Event;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.EventsService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class EventsBean implements Serializable {
    private static final long serialVersionUID = 5831093403743197357L;
    @Inject
    private EventsService eventsService;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private List<Event> events;
    private Event event;

    @PostConstruct
    public void init() {
        user = userService.getUserById(user.getId());
        loadEvents();
    }

    private void loadEvents() {
        events = eventsService.getAllEvents();
    }

    public void newEvent() {
        event = new Event();
        event.setCreatedAt(new Date());
        event.setCreatedBy(user);
        events.add(event);
    }

    public void delete() {
        eventsService.delete(event);
        event = null;
        loadEvents();
    }

    public void onEventSelect() {
        // empty
    }

    public void save() {
        eventsService.save(event);
        event = null;
        loadEvents();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                    "Akce byla uložena"));
    }

    public void cancelEdit() {
        event = null;
        loadEvents();
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
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }
}

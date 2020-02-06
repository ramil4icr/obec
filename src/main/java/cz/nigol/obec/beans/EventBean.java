package cz.nigol.obec.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Event;
import cz.nigol.obec.services.EventsService;

@Named
@RequestScoped
public class EventBean {
    @Inject
    private EventsService eventsService;
    @Inject
    private FacesContext facesContext;
    private String ical;
    private String id;
    private Event event;

    public void onLoad() throws IOException {
        event = eventsService.getById(Long.parseLong(id));
        if (ical != null && "true".equals(ical)) {
            generateIcal();
        }
    }

    private void generateIcal() throws IOException {
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseHeader("Content-Type", "text/calendar; charset=utf-8");
        externalContext.setResponseHeader("Content-Disposition", "inline;filename=event.ics");
        OutputStream outputStream = externalContext.getResponseOutputStream();
        outputStream.write(eventsService.getAsIcal(event).getBytes(Charset.forName("UTF-8")));
        outputStream.close();
        facesContext.responseComplete();
    }

    /**
     * @return the ical
     */
    public String getIcal() {
        return ical;
    }

    /**
     * @param ical the ical to set
     */
    public void setIcal(String ical) {
        this.ical = ical;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

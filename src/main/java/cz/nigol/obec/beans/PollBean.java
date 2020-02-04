package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.PollService;

@Named
@ViewScoped
public class PollBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private PollService pollService;
    @Inject
    private FacesContext facesContext;
    @Inject
    private SessionBean sessionBean;
    private Poll poll;
    private long id;

    public void onLoad() {
        poll = pollService.findById(id);
    }

    public void vote(PollQuestion question) {
        PollAnswer answer = new PollAnswer();
        answer.setCreatedAt(new Date());
        answer.setPollQuestion(question);
        pollService.savePollAnswer(answer);
        sessionBean.setPollPerformed(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", 
                    "Děkujeme za Váš hlas pro volbu '" + question.getQuestion() + "'."));
    }

    /**
     * @return the poll
     */
    public Poll getPoll() {
        return poll;
    }

    /**
     * @param poll the poll to set
     */
    public void setPoll(Poll poll) {
        this.poll = poll;
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
}

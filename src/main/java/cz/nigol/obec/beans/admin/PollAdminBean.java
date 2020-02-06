package cz.nigol.obec.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Poll;
import cz.nigol.obec.entities.PollQuestion;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.qualifiers.LoggedUser;
import cz.nigol.obec.services.PollService;
import cz.nigol.obec.services.UserService;

@Named
@ViewScoped
public class PollAdminBean implements Serializable {
    private static final long serialVersionUID = -7906523978360505956L;
    @Inject
    private PollService pollService;
    @Inject
    private FacesContext facesContext;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    private List<Poll> polls;
    private Poll poll;
    // There will be 4 questions hardcoded as temporary solution (maybe permanent :))
    private PollQuestion question1;
    private PollQuestion question2;
    private PollQuestion question3;
    private PollQuestion question4;

    @PostConstruct
    public void init() {
        user = userService.getUserById(user.getId());
        loadAll();
    }

    private void loadAll() {
        polls = pollService.getAllPolls();
    }

    public void newPoll() {
        poll = new Poll();
        poll.setPollQuestions(new ArrayList<>());
        poll.setCreatedAt(new Date());
        poll.setCreatedBy(user);
        prepareQuestions();
        polls.add(poll);
    }

    private void prepareQuestions() {
        question1 = new PollQuestion();
        question2 = new PollQuestion();
        question3 = new PollQuestion();
        question4 = new PollQuestion();
    }

    public void savePoll() {
        if (!"".equals(question1.getQuestion())) {
            poll.getPollQuestions().add(question1);
        }
        if (!"".equals(question2.getQuestion())) {
            poll.getPollQuestions().add(question2);
        }
        if (!"".equals(question3.getQuestion())) {
            poll.getPollQuestions().add(question3);
        }
        if (!"".equals(question4.getQuestion())) {
            poll.getPollQuestions().add(question4);
        }
        pollService.savePoll(poll);
        poll = null;
        loadAll();
        prepareQuestions();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Anketa byla uložena."));
    }

    public void onPollSelect() {
        int count = poll.getPollQuestions().size();
        prepareQuestions();
        switch (count) {
            case 4:
                question4 = poll.getPollQuestions().get(3);
            case 3:
                question3 = poll.getPollQuestions().get(2);
            case 2:
                question2 = poll.getPollQuestions().get(1);
            case 1:
                question1 = poll.getPollQuestions().get(0);
            default:
        }
        poll.setPollQuestions(new ArrayList<>());
    }

    public void cancelEdit() {
        poll = null;
        loadAll();
        prepareQuestions();
    }

    /**
     * @return the polls
     */
    public List<Poll> getPolls() {
        return polls;
    }

    /**
     * @param polls the polls to set
     */
    public void setPolls(List<Poll> polls) {
        this.polls = polls;
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
     * @return the question1
     */
    public PollQuestion getQuestion1() {
        return question1;
    }

    /**
     * @param question1 the question1 to set
     */
    public void setQuestion1(PollQuestion question1) {
        this.question1 = question1;
    }

    /**
     * @return the question2
     */
    public PollQuestion getQuestion2() {
        return question2;
    }

    /**
     * @param question2 the question2 to set
     */
    public void setQuestion2(PollQuestion question2) {
        this.question2 = question2;
    }

    /**
     * @return the question3
     */
    public PollQuestion getQuestion3() {
        return question3;
    }

    /**
     * @param question3 the question3 to set
     */
    public void setQuestion3(PollQuestion question3) {
        this.question3 = question3;
    }

    /**
     * @return the question4
     */
    public PollQuestion getQuestion4() {
        return question4;
    }

    /**
     * @param question4 the question4 to set
     */
    public void setQuestion4(PollQuestion question4) {
        this.question4 = question4;
    }
}

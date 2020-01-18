package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.Poll;
import cz.nigol.obec.services.PollService;

@Named
@RequestScoped
public class PollResultsBean {
    @Inject
    private PollService pollService;
    private Poll poll;

    public void loadPoll(long pollId) {
        poll = pollService.findById(pollId);
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
}

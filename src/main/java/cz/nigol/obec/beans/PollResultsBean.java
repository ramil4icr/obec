package cz.nigol.obec.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.*;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.PollService;

@Named
@RequestScoped
public class PollResultsBean {
    @Inject
    private PollService pollService;
    private Poll poll;
    private int totalVotes;

    public void loadPoll(long pollId) {
        poll = pollService.findById(pollId);
        totalVotes = poll.getPollQuestions().stream()
            .map(q -> q.getPollAnswers().size())
            .reduce(0, Integer::sum);
    }

    public int answerPercentage(PollQuestion question) {
        if (totalVotes == 0) {
            return 0;
        }
        return question.getPollAnswers().size() * 100 / totalVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
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

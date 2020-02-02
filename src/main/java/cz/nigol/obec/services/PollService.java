package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.*;

public interface PollService {
    Poll findById(long id);
    Poll getActivePoll();
    Poll savePoll(Poll poll);
    List<Poll> getAllPolls();
    void savePollAnswer(PollAnswer pollAnswer);
}

package cz.nigol.obec.services;

import java.util.*;
import cz.nigol.obec.entities.*;

public interface CouncillorService {
    List<Councillor> getAllCouncillors();
    List<CouncilMeeting> getAllCouncilMeetings();
    List<ElectionPeriod> getAllElectionPeriods();
    void saveCouncillor(Councillor councillor);
    void saveCouncilMeeting(CouncilMeeting councilMeeting);
    void saveElectionPeriod(ElectionPeriod electionPeriod);
    Councillor findCouncillorById(long id);
}
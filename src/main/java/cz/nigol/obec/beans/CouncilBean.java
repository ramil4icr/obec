package cz.nigol.obec.beans;

import java.io.*;
import java.util.*;
import javax.inject.*;
import javax.faces.view.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;
import java.util.stream.*;

@Named
@ViewScoped
public class CouncilBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private CouncillorService councillorService;
    @Inject
    private FacesContext facesContext;
    private List<ElectionPeriod> electionPeriods;
    private ElectionPeriod electionPeriod;
    private List<Councillor> councillors;
    private List<Councillor> leaders;

    @PostConstruct
    public void init() {
        electionPeriods = councillorService.getAllElectionPeriods();    
        if (electionPeriods.size() != 0) {
            electionPeriod = electionPeriods.get(0);     
            prepareCouncillors();
        }
    }

    private void prepareCouncillors() {
        councillors = electionPeriod.getCouncillors();
            leaders = councillors.stream()
                .filter(Councillor::isLeader)
                .collect(Collectors.toList());
            councillors = councillors.stream()
                .filter(c -> !c.isLeader())
                .collect(Collectors.toList());
    }

    public void onElectionPeriodSelect() {
        prepareCouncillors();
    }

    public List<ElectionPeriod> getElectionPeriods() {
        return electionPeriods;
    }

    public void setElectionPeriods(List<ElectionPeriod> electionPeriods) {
        this.electionPeriods = electionPeriods;
    }

    public List<Councillor> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<Councillor> leaders) {
        this.leaders = leaders;
    }

    public List<Councillor> getCouncillors() {
        return councillors;
    }

    public void setCouncillors(List<Councillor> councillors) {
        this.councillors = councillors;
    }

    public ElectionPeriod getElectionPeriod() {
        return electionPeriod;
    }

    public void setElectionPeriod(ElectionPeriod electionPeriod) {
        this.electionPeriod = electionPeriod;
    }
}
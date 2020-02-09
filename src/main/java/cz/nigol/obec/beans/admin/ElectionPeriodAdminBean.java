package cz.nigol.obec.beans.admin;

import java.io.*;
import java.util.*;
import javax.inject.*;
import javax.faces.view.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Named
@ViewScoped
public class ElectionPeriodAdminBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private CouncillorService councillorService;
    @Inject
    private FacesContext facesContext;
    private List<ElectionPeriod> electionPeriods;
    private ElectionPeriod electionPeriod;

    @PostConstruct
    public void init() {
        electionPeriods = councillorService.getAllElectionPeriods();    
    }

    public void newElectionPeriod() {
        electionPeriod = new ElectionPeriod();
        electionPeriods.add(electionPeriod);     
    }

    public void cancelEdit() {
        electionPeriod = null;
        init();     
    }

    public void onElectionPeriodSelect() {
             
    }

    public void saveElectionPeriod() {
        councillorService.saveElectionPeriod(electionPeriod);     
        electionPeriod = null;
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Volební období bylo uloženo."));
    }

    public ElectionPeriod getElectionPeriod() {
        return electionPeriod;     
    }

    public void setElectionPeriod(ElectionPeriod electionPeriod) {
        this.electionPeriod = electionPeriod;     
    }

    public List<ElectionPeriod> getElectionPeriods() {
        return electionPeriods;     
    }

    public void setElectionPeriods(List<ElectionPeriod> electionPeriods) {
        this.electionPeriods = electionPeriods;     
    }
}
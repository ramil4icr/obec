package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name=Councillor.GET_ALL, query="SELECT c FROM Councillor c ORDER BY c.id DESC"),
    @NamedQuery(name=Councillor.GET_BY_USER, query="SELECT c FROM Councillor c WHERE c.user = :user"),
})
@Entity
@Table(name="OB_COUNCILLOR")
public class Councillor implements Serializable {
    private static final long serialVersionUID = -4378072789330339015L;

    public static final String GET_ALL = "Councillor.GET_ALL";
    public static final String GET_BY_USER = "Councillor.GET_BY_USER";

    public static final String USER_PARAM = "user";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="OCCUPATION")
    private String occupation;

    @Column(name="LEADER")
    private boolean leader;

    @Column(name="NOTE")
    private String note;

    @Column(name="START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name="END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name="PRIORITY")
    private int priority;

    @Column(name="RETIRED")
    private boolean retired;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ELECTION_PERIOD_ID")
    private ElectionPeriod electionPeriod;

    @ManyToMany(mappedBy="councillors", fetch=FetchType.EAGER)
    private List<CouncilMeeting> councilMeetings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @OrderBy("id DESC")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<CouncilMeeting> getCouncilMeetings() {
        return councilMeetings;     
    }

    public void setCouncilMeetings(List<CouncilMeeting> councilMeetings) {
        this.councilMeetings = councilMeetings;     
    }

    public ElectionPeriod getElectionPeriod() {
        return electionPeriod;     
    }

    public void setElectionPeriod(ElectionPeriod electionPeriod) {
        this.electionPeriod = electionPeriod;     
    }

    public User getUser() {
        return user;     
    }

    public void setUser(User user) {
        this.user = user;     
    }

    public boolean isRetired() {
        return retired;     
    }

    public void setRetired(boolean retired) {
        this.retired = retired;     
    }

    public int getPriority() {
        return priority;     
    }

    public void setPriority(int priority) {
        this.priority = priority;     
    }

    public Date getEndDate() {
        return endDate;    
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;     
    }

    public Date getStartDate() {
        return startDate;     
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;     
    }

    public String getNote() {
        return note;     
    }

    public void setNote(String note) {
        this.note = note;     
    }

    public boolean isLeader() {
        return leader;     
    }

    public void setLeader(boolean leader) {
        this.leader = leader;     
    }

    public String getOccupation() {
        return occupation;     
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;     
    }
    
    public long getId() {
        return id;     
    }

    public void setId(long id) {
        this.id = id;     
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Councillor)) return false;
        Councillor councillor = (Councillor) o;
        return id == councillor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
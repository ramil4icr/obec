package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name=ElectionPeriod.GET_ALL, query="SELECT ep FROM ElectionPeriod ep ORDER BY ep.active DESC, ep.id DESC"),
})
@Entity
@Table(name="OB_ELECTION_PERIOD")
public class ElectionPeriod implements Serializable {
    private static final long serialVersionUID = -4378072789330339015L;

    public static final String GET_ALL = "ElectionPeriod.GET_ALL";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="LABEL")
    private String label;

    @Column(name="ACTIVE")
    private boolean active;

    @OneToMany(mappedBy="electionPeriod", cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    @OrderBy("priority")
    private List<Councillor> councillors;

    @OneToMany(mappedBy="electionPeriod", cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<CouncilMeeting> councilMeetings;

    public List<CouncilMeeting> getCouncilMeetings() {
        return councilMeetings;     
    }

    public void setCouncilMeetings(List<CouncilMeeting> councilMeetings) {
        this.councilMeetings = councilMeetings;     
    }

    public List<Councillor> getCouncillors() {
        return councillors;     
    } 

    public void setCouncillors(List<Councillor> councillors) {
        this.councillors = councillors;     
    }

    public boolean isActive() {
        return active;     
    }

    public void setActive(boolean active) {
        this.active = active;     
    }

    public String getLabel() {
        return label;     
    }

    public void setLabel(String label) {
        this.label = label;     
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
        if (!(o instanceof ElectionPeriod)) return false;
        ElectionPeriod electionPeriod = (ElectionPeriod) o;
        return id == electionPeriod.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
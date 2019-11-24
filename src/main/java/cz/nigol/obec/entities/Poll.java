package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
@NamedQuery(name=Poll.GET_ALL, query="SELECT p FROM Poll p ORDER BY p.createdAt DESC"),
})
@Entity
@Table(name="OB_POLL")
public class Poll implements Serializable {
    private static final long serialVersionUID = 2441041195650043052L;

    public static final String GET_ALL = "Poll.GET_ALL";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User createdBy;

    @Column(name="LABEL", columnDefinition="VARCHAR(300)")
    private String label;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<PollQuestion> pollQuestions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poll)) return false;
        Poll poll = (Poll) o;
        return id == poll.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the pollQuestions
     */
    public List<PollQuestion> getPollQuestions() {
        return pollQuestions;
    }

    /**
     * @param pollQuestions the pollQuestions to set
     */
    public void setPollQuestions(List<PollQuestion> pollQuestions) {
        this.pollQuestions = pollQuestions;
    }
}

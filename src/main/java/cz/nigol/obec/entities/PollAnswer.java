package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OB_POLL_ANSWER")
public class PollAnswer implements Serializable {
    private static final long serialVersionUID = -5668838581669260876L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="FINGERPRINT")
    private String fingerPrint;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="POLL_QUESTION_ID")
    private PollQuestion pollQuestion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PollAnswer)) return false;
        PollAnswer pollAnswer = (PollAnswer) o;
        return id == pollAnswer.getId();
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
     * @return the fingerPrint
     */
    public String getFingerPrint() {
        return fingerPrint;
    }

    /**
     * @param fingerPrint the fingerPrint to set
     */
    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
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
     * @return the pollQuestion
     */
    public PollQuestion getPollQuestion() {
        return pollQuestion;
    }

    /**
     * @param pollQuestion the pollQuestion to set
     */
    public void setPollQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }
}

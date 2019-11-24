package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OB_POLL_QUESTION")
public class PollQuestion implements Serializable {
    private static final long serialVersionUID = 9014170936154731857L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="QUESTION", columnDefinition="VARCHAR(300)")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    private List<PollAnswer> pollAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PollQuestion)) return false;
        PollQuestion pollQuestion = (PollQuestion) o;
        return id == pollQuestion.getId();
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
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the pollAnswers
     */
    public List<PollAnswer> getPollAnswers() {
        return pollAnswers;
    }

    /**
     * @param pollAnswers the pollAnswers to set
     */
    public void setPollAnswers(List<PollAnswer> pollAnswers) {
        this.pollAnswers = pollAnswers;
    }
}

package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name=MailLog.GET_ALL, query="SELECT m FROM MailLog m ORDER BY m.sentAt"),
})
@Entity
@Table(name="OB_MAIL_LOG")
public class MailLog implements Serializable {
    private static final long serialVersionUID = -4378072789330339015L;

    public static final String GET_ALL = "MailLog.GET_ALL";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="SENT_TO")
    private String to;

    @Column(name="SUBJECT")
    private String subject;

    @Column(name="SENT_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
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
        if (!(o instanceof MailLog)) return false;
        MailLog mailLog = (MailLog) o;
        return id == mailLog.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
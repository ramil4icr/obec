package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name=HelpOffer.GET_ALL, query="SELECT h FROM HelpOffer h ORDER BY h.id DESC"),
})
@Entity
@Table(name="OB_HELP_OFFER")
public class HelpOffer implements Serializable {
    private static final long serialVersionUID = -4378072789330339015L;

    public static final String GET_ALL = "HelpOffer.GET_ALL";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="FULLNAME")
    private String fullname;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;

    @Column(name="AGREED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreedAt;

    @Column(name="OFFER")
    private String offer;

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Date getAgreedAt() {
        return agreedAt;
    }

    public void setAgreedAt(Date agreedAt) {
        this.agreedAt = agreedAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
        if (!(o instanceof HelpOffer)) return false;
        HelpOffer helpOffer = (HelpOffer) o;
        return id == helpOffer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
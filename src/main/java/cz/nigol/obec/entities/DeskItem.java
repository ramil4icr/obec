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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name=DeskItem.GET_ALL,
		    query="SELECT d FROM DeskItem d ORDER BY d.activeFrom DESC"),
	    @NamedQuery(name=DeskItem.GET_ACTIVE_TO_DATE,
			query="SELECT d FROM DeskItem d WHERE :date BETWEEN d.activeFrom AND d.activeTo ORDER BY d.activeFrom"),
    })
@Entity
@Table(name="OB_DESK_ITEM")
public class DeskItem implements Serializable {
    private static final long serialVersionUID = 8396396135912529543L;

    public static final String GET_ALL = "DeskItem.GET_ALL";
    public static final String GET_ACTIVE_TO_DATE = "DeskItem.GET_ACTIVE_TO_DATE";

    public static final String DATE_PARAM = "date";

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

    @Column(name="BODY", columnDefinition="VARCHAR(2000)")
    private String body;

    @Column(name="ACTIVE_FROM")
    @Temporal(TemporalType.DATE)
    private Date activeFrom;

    @Column(name="ACTIVE_TO")
    @Temporal(TemporalType.DATE)
    private Date activeTo;

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
     * @return the body
     */
    public String getBody() {
	return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
	this.body = body;
    }

    /**
     * @return the activeFrom
     */
    public Date getActiveFrom() {
	return activeFrom;
    }

    /**
     * @param activeFrom the activeFrom to set
     */
    public void setActiveFrom(Date activeFrom) {
	this.activeFrom = activeFrom;
    }

    /**
     * @return the activeTo
     */
    public Date getActiveTo() {
	return activeTo;
    }

    /**
     * @param activeTo the activeTo to set
     */
    public void setActiveTo(Date activeTo) {
	this.activeTo = activeTo;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof DeskItem)) return false;
	DeskItem deskItem = (DeskItem) o;
	return id == deskItem.getId();
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }
}

package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name=Article.GET_ALL, query="SELECT a FROM Article a ORDER BY a.changedAt DESC"),
	    @NamedQuery(name=Article.GET_BY_USER,
			query="SELECT a FROM Article a WHERE a.changedBy = :user ORDER BY a.changedAt DESC"),
	    })
@Entity
@Table(name = "OB_ARTICLE")
public class Article implements Serializable {
    private static final long serialVersionUID = 5517765996360052018L;

    public static final String GET_ALL = "Article.GET_ALL";
    public static final String GET_BY_USER = "Article.GET_BY_USER";

    public static final String USER_PARAM = "user";

    @Id
    @Column(name="ID", columnDefinition="VARCHAR(300)")
    private String id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User changedBy;

    @Column(name="BODY")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String body;

    @Column(name="LABEL", columnDefinition="VARCHAR(200)")
    private String label;

    @Column(name="CHANGED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt;

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }

	/**
	 * @return the changedBy
	 */
	public User getChangedBy() {
		return changedBy;
	}

	/**
	 * @param changedBy the changedBy to set
	 */
	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
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
     * @return the changedAt
     */
    public Date getChangedAt() {
	return changedAt;
    }

    /**
     * @param changedAt the changedAt to set
     */
    public void setChangedAt(Date changedAt) {
	this.changedAt = changedAt;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Article)) return false;
	Article article = (Article) o;
	return id.equals(article.getId());
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }
}

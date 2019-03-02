package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
@NamedQuery(name=User.GET_ALL, query="SELECT u FROM User u ORDER BY u.id ASC"),
    @NamedQuery(name=User.GET_ACTIVE, query="SELECT u FROM User u WHERE u.active = true"),
    @NamedQuery(name=User.GET_ACTIVE_BY_USER_ID, query="SELECT u FROM User u WHERE u.userId = :userId AND u.active = true"),
    @NamedQuery(name=User.GET_BY_USER_ID, query="SELECT u FROM User u WHERE u.userId = :userId"),
})
@Entity
@Table(name = "OB_USER")
public class User implements Serializable {
    private static final long serialVersionUID = -8877869295129979225L;

    public static final String GET_ALL = "User.GET_ALL";
    public static final String GET_ACTIVE = "User.GET_ACTIVE";
    public static final String GET_BY_USER_ID = "User.GET_BY_USER_ID";
    public static final String GET_ACTIVE_BY_USER_ID = "User.GET_ACTIVE_BY_USER_ID";

    public static final String USER_ID_PARAM = "userId";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="USER_ID", columnDefinition="VARCHAR(50)")
        private String userId;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="EMAIL", columnDefinition="VARCHAR(300)")
        private String email;

    @Column(name="FULL_NAME", columnDefinition="VARCHAR(200)")
        private String fullName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="ACTIVE")
    private boolean active;

    @OneToOne
    private Role role;

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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

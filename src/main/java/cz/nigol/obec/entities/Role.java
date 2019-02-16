package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OB_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = 4244545735191926653L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Path> paths;

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
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the paths
     */
    public Set<Path> getPaths() {
	return paths;
    }

    /**
     * @param paths the paths to set
     */
    public void setPaths(Set<Path> paths) {
	this.paths = paths;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Role)) return false;
	Role role = (Role) o;
	return id == role.getId();
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }
}

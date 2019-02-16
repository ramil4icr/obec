package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OB_PATH")
public class Path implements Serializable {
    private static final long serialVersionUID = -2834494297578582050L;

    @Id
    @Column(name="ID")
    private String id;

    public Path() {
	// default constructor
    }

    public Path(String path) {
	this.id = path;
    }

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

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Path)) return false;
	Path path = (Path) o;
	return id == path.getId();
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }
}

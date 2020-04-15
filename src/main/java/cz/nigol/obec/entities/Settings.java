package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OB_SETTINGS")
public class Settings implements Serializable {
    private static final long serialVersionUID = -3262642900499913785L;

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="BASE_URL")
    private String baseUrl;

    @Column(name="OG_IMAGE_URL")
    private String ogImageUrl;

    @Column(name="IMPORTANT_MSG")
    private String importantMsg;

    @Column(name="IMPORTANT_MSG_URL")
    private String importantMsgUrl;

    public String getImportantMsgUrl() {
        return importantMsgUrl;
    }

    public void setImportantMsgUrl(String importantMsgUrl) {
        this.importantMsgUrl = importantMsgUrl;
    }

    public String getImportantMsg() {
        return importantMsg;
    }

    public void setImportantMsg(String importantMsg) {
        this.importantMsg = importantMsg;
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

    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return the ogImageUrl
     */
    public String getOgImageUrl() {
        return ogImageUrl;
    }

    /**
     * @param ogImageUrl the ogImageUrl to set
     */
    public void setOgImageUrl(String ogImageUrl) {
        this.ogImageUrl = ogImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settings)) return false;
        Settings settings = (Settings) o;
        return id == settings.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

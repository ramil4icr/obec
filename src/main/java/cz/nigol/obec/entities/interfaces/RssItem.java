package cz.nigol.obec.entities.interfaces;

import java.util.Date;

public interface RssItem {
    String getLabel();
    String getDescription();
    Date getDate();
    String getGuid();
}

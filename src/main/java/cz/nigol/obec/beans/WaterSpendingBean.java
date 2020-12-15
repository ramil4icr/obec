package cz.nigol.obec.beans;

import java.io.*;
import java.util.*;
import javax.inject.*;
import javax.faces.view.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;
import cz.nigol.obec.qualifiers.*;

@Named
@ViewScoped
public class WaterSpendingBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private WaterSpendingService waterSpendingService;
    @Inject
    private FacesContext facesContext;
    @Inject
    @CurrentSettings
    private Settings settings;
    private WaterSpending waterSpending;
    private boolean sent;

    @PostConstruct
    public void init() {
        waterSpending = new WaterSpending();
        waterSpending.setPeriod(settings.getWaterSpendPeriod());
        waterSpending.setCreatedAt(new Date());
        sent = false;
    }

    public void save() {
        waterSpendingService.saveWaterSpending(waterSpending);
        init();
        sent = true;
    }
    
    public boolean isRenderForm() {
        return !sent && (settings.getWaterSpendPeriod() != null && !"".equals(settings.getWaterSpendPeriod()));
    }
    
    public Settings getSettings() {
        return settings;
    }

    public boolean getSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public WaterSpending getWaterSpending() {
        return waterSpending;
    }

    public void setWaterSpending(WaterSpending waterSpending) {
        this.waterSpending = waterSpending;
    }
}
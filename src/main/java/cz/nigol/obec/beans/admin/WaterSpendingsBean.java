package cz.nigol.obec.beans.admin;

import java.io.*;
import java.util.*;
import javax.inject.*;
import javax.faces.view.*;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.annotation.*;
import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Named
@ViewScoped
public class WaterSpendingsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private WaterSpendingService waterSpendingService;
    @Inject
    private FacesContext facesContext;
    private List<WaterSpending> waterSpendings;

    @PostConstruct
    public void init() {
        waterSpendings = waterSpendingService.getAllWaterSpendings();
    }

    public List<WaterSpending> getWaterSpendings() {
        return waterSpendings;
    }

    public void setWaterSpendings(List<WaterSpending> waterSpendings) {
        this.waterSpendings = waterSpendings;
    }
}
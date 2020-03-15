package cz.nigol.obec.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.obec.entities.*;
import cz.nigol.obec.services.*;

@Named
@ApplicationScoped
public class ElectionPeriodConverter implements Converter {
    @Inject
    private CouncillorService councillorService;

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        ElectionPeriod result = null;
        if (arg2 != null && !"".equals(arg2)) {
            result = councillorService.findElectionPeriodById(Long.parseLong(arg2));
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String result = "";
        if (arg2 != null) {
            ElectionPeriod electionPeriod = (ElectionPeriod) arg2;
            result = result + electionPeriod.getId();
        }
        return result;
    }
}

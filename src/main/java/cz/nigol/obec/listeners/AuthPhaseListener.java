package cz.nigol.obec.listeners;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cz.nigol.obec.beans.SessionBean;
import cz.nigol.obec.entities.Path;
import cz.nigol.obec.entities.User;

@Named
@RequestScoped
public class AuthPhaseListener implements PhaseListener {
    public static final long serialVersionUID = 1L;
    @Inject
    private Log log;
    @Inject
    private SessionBean sessionBean;

    public void beforePhase(PhaseEvent pe) {
        HttpServletRequest req = (HttpServletRequest) pe.getFacesContext().getExternalContext().getRequest();
        String uri = req.getRequestURI();
        User user = sessionBean.getUser();
        boolean reject = false;
        if (uri.contains("admin") || uri.contains("uzivatel")) {
            Path pt = new Path();
            pt.setId(req.getServletPath());
            reject = user == null || !user.getRole().getPaths().contains(pt);
        }
        if (reject) {
            FacesContext facesContext = pe.getFacesContext();
            facesContext.getApplication().getNavigationHandler()
                .handleNavigation(facesContext, null, "prihlaseni.xhtml?faces-redirect=true");
        }
    }

    public void afterPhase(PhaseEvent pe) {

    }

    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}

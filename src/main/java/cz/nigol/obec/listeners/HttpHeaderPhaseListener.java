package cz.nigol.obec.listeners;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@Named
@ApplicationScoped
public class HttpHeaderPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -1656587697453854944L;

    @Override
    public void afterPhase(PhaseEvent arg0) {
        // nothing to do
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        HttpServletResponse response = (HttpServletResponse) event.getFacesContext()
            .getExternalContext().getResponse();
        response.addHeader("X-XSS-Protection", "1; mode=block");
        response.addHeader("X-Frame-Options", "SAMEORIGIN");
        response.addHeader("X-Content-Type-Options", "nosniff");
        response.addHeader("Referrer-Policy", "no-referrer-when-downgrade");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}

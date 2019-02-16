package cz.nigol.obec.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import cz.nigol.obec.entities.Path;

@Named
@ApplicationScoped
public class ApplicationBean {
    private Map<Integer, String> daysOfWeek;
    private Set<Path> securedPaths;

    @PostConstruct
    public void init() {
	prepareDaysOfWeek();
	preparePaths();
    }

    private void preparePaths() {
	securedPaths = new HashSet<>();
	securedPaths.add(new Path("/administrace/clanky.jsf"));
	securedPaths.add(new Path("/administrace/nahled.jsf"));
	securedPaths.add(new Path("/obec/aktuality/administrace.jsf"));
	securedPaths.add(new Path("/obecni-urad/rozhlas/administrace.jsf"));
	securedPaths.add(new Path("/obecni-urad/uredni-deska/administrace.jsf"));
    }

    private void prepareDaysOfWeek() {
	daysOfWeek = new HashMap<>();
	daysOfWeek.put(1, "neděle");
	daysOfWeek.put(2, "pondělí");
	daysOfWeek.put(3, "úterý");
	daysOfWeek.put(4, "středa");
	daysOfWeek.put(5, "čtvrtek");
	daysOfWeek.put(6, "pátek");
	daysOfWeek.put(7, "sobota");
    }

    public Date today() {
	return new Date();
    }

    public String todayNameOfDay() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(today());
	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	return daysOfWeek.get(dayOfWeek);
    }

    /**
     * @return the securedPaths
     */
    public Set<Path> getSecuredPaths() {
	return securedPaths;
    }
}

package cz.nigol.obec.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class IndexBean {

    @PostConstruct
    public void init() {
    }

}

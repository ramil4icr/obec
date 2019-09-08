package cz.nigol.obec.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.nigol.obec.entities.Settings;
import cz.nigol.obec.services.SettingsService;

@Stateless
public class SettingsServiceImpl implements SettingsService {
    @PersistenceContext(unitName="obecPU")
    private EntityManager em;

    @Override
    public Settings findById(String id) {
        return em.find(Settings.class, id);
    }

    @Override
    public Settings save(Settings settings) {
        return em.merge(settings);
    }
}

package cz.nigol.obec.services;

import cz.nigol.obec.entities.Settings;

public interface SettingsService {
    Settings findById(String id);
    Settings save(Settings settings);
}

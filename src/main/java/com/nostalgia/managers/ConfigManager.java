package com.nostalgia.managers;

import com.nostalgia.PluginLoader;
public class ConfigManager {

    public ConfigManager() {
        PluginLoader.getInstance().saveDefaultConfig();
    }

    public String getModeType() {
        return PluginLoader.getInstance().getConfig().getString("mode");
    }

}

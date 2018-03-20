package ca.ame94.chestregistry.util;

import org.bukkit.plugin.java.JavaPlugin;

public class Config {

    private static JavaPlugin plugin = PluginMgr.getPlugin();


    public static void Init() {

        if (!plugin.getDataFolder().exists()) {
            Logger.Info("Creating default configuration...");
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
    }

    public static String getPluginFolder() {
        return plugin.getDataFolder().toString();
    }
}

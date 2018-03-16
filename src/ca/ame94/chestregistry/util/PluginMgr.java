package ca.ame94.chestregistry.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMgr {

    private static PluginManager pluginMgr;
    private static JavaPlugin pluginRef;

    /**
     * Returns the plugin reference
     * @return The plugin instance
     */
    public static JavaPlugin getPlugin() {
        return pluginRef;
    }

    /**
     * Supply this helper class with the plugin instance
     * @param p The plugin instance
     */
    public static void Init(JavaPlugin p) {
        pluginMgr = Bukkit.getPluginManager();
        pluginRef = p;
    }

    /**
     * Register an event listener
     * @param listener The listener
     */
    public static void RegisterEvent(Listener listener) {
        pluginMgr.registerEvents(listener, pluginRef);
    }

    /**
     * Reload the plugin
     */
    public static void Reload() {
        pluginRef.reloadConfig();
    }
}

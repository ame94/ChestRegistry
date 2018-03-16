package ca.ame94.chestregistry.util;

import org.bukkit.Bukkit;
import java.util.logging.Level;

/**
 * Basic plugin logging method
 */
public class Logger {

    /**
     * Log a message as informational
     * @param msg The message to log
     */
    public static void Info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[ChestRegistry] " + msg);
    }

    /**
     * Log a message as a warning
     * @param msg The message to log
     */
    public static void Warn(String msg) {
        Bukkit.getLogger().log(Level.WARNING, "[ChestRegistry] " + msg);
    }

    /**
     * Log a message as being a critical error
     * @param msg The message to log
     */
    public static void Error(String msg) {
        Bukkit.getLogger().log(Level.SEVERE, "[ChestRegistry] " + msg);
    }

    /**
     * Send a message to broadcast to all online players
     * @param msg The message to broadcast
     */
    public static void BroadcastAndLog(String msg) {
        PluginMgr.getPlugin().getServer().broadcastMessage(msg);
        Info(msg);
    }

}

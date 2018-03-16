package ca.ame94.chestregistry;

import ca.ame94.chestregistry.util.Logger;
import ca.ame94.chestregistry.util.PluginMgr;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginMgr.Init(this);
        Logger.Info("Starting up!");
    }

    @Override
    public void onDisable() {
        Logger.Info("Shutting down.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return false;
    }
}

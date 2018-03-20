package ca.ame94.chestregistry;

import ca.ame94.chestregistry.listener.BlockBreakListener;
import ca.ame94.chestregistry.listener.BlockPlaceListener;
import ca.ame94.chestregistry.util.ChestManager;
import ca.ame94.chestregistry.util.Config;
import ca.ame94.chestregistry.util.Logger;
import ca.ame94.chestregistry.util.PluginMgr;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.Info("Starting up!");
        PluginMgr.Init(this);
        Config.Init();
        ChestManager.Init();

        // Register events
        PluginMgr.RegisterEvent(new BlockPlaceListener());
        PluginMgr.RegisterEvent(new BlockBreakListener());
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

package ca.ame94.chestregistry.handler;

import ca.ame94.chestregistry.PlayerChest;
import ca.ame94.chestregistry.util.Chest;
import ca.ame94.chestregistry.util.ChestManager;
import ca.ame94.chestregistry.util.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public class ChestRemoval {

    public static void process(BlockBreakEvent e) {

        Logger.Info("BlockBreakEvent");

        Location loc = e.getBlock().getLocation();
        String world = e.getBlock().getWorld().getName();
        Material mat = e.getBlock().getType();

        // Determine if this newly placed chest is placed next to another chest (turned into a large chest)
        if (Chest.isLargeChest(loc, mat)) {
            Logger.Info("Was part of large chest, not removing");
        } else {
            //ChestManager.insertChestEntry(new PlayerChest(e.getBlock().getLocation(), e.getPlayer().getUniqueId()));
            ChestManager.removeChestEntry(new PlayerChest(e.getBlock().getLocation(), e.getPlayer().getUniqueId()));
        }


        // temporary informational data
        int chestCount = ChestManager.getNumChests();
        e.getPlayer().sendMessage("There are " + chestCount + " chests in the db");

    }

}

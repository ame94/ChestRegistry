package ca.ame94.chestregistry.handler;

import ca.ame94.chestregistry.PlayerChest;
import ca.ame94.chestregistry.util.Chest;
import ca.ame94.chestregistry.util.ChestManager;
import ca.ame94.chestregistry.util.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChestPlacement {

    /**
     * The placed block is a chest, register it.
     * @param e The event
     */
    public static void process(BlockPlaceEvent e) {
        Location loc = e.getBlock().getLocation();
        String world = e.getBlock().getWorld().getName();
        Material mat = e.getBlock().getType();

        // Determine if this newly placed chest is placed next to another chest (turned into a large chest)
        if (Chest.isLargeChest(loc, mat)) {
            Logger.Info("Small chest expanded, not adding new entry");
        } else {
            ChestManager.insertChestEntry(new PlayerChest(e.getBlock().getLocation(), e.getPlayer().getUniqueId()));
        }

        // temporary informational data
        int chestCount = ChestManager.getNumChests();
        e.getPlayer().sendMessage("There are " + chestCount + " chests in the db");
    }

}

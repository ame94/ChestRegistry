package ca.ame94.chestregistry.listener;

import ca.ame94.chestregistry.handler.ChestPlacement;
import ca.ame94.chestregistry.handler.ChestRemoval;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Material mat = e.getBlock().getType();

        // Handle events for chest placement
        if (mat == Material.CHEST || mat == Material.TRAPPED_CHEST) {
            ChestRemoval.process(e);
        }
    }


}

package ca.ame94.chestregistry.listener;

import ca.ame94.chestregistry.handler.ChestPlacement;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Material mat = e.getBlock().getType();

        // Handle events for chest placement
        if (mat == Material.CHEST || mat == Material.TRAPPED_CHEST) {
            ChestPlacement.process(e);
        }
    }

}

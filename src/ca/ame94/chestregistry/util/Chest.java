package ca.ame94.chestregistry.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Chest {

    private static BlockFace[] cardinalDirections = {BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH, BlockFace.EAST};

    /**
     * Check if there is another chestblock along the cardinal directions relative to the location given.
     * @param location The location to check at
     * @param queriedChestType This will be a normal or trapped chest
     * @return true if there is a chest nearby.
     */
    public static boolean isLargeChest(Location location, Material queriedChestType) {
        Material nearbyChestType;
        boolean result = false;

        for (BlockFace direction : cardinalDirections) {
            nearbyChestType = location.getBlock().getRelative(direction).getType();
            if (nearbyChestType == queriedChestType) {
                result = true;
                break;
            }
        }

        return result;
    }


}

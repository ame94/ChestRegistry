package ca.ame94.chestregistry;

import org.bukkit.Location;

import java.util.UUID;

/**
 * Association object between a chest and a player
 */
public class PlayerChest {

    private Location location;
    private UUID playerUUID;

    public PlayerChest(Location location, UUID playerUUID) {
        this.location = location;
        this.playerUUID = playerUUID;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }
}


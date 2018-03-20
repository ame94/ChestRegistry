package ca.ame94.chestregistry.util;

import ca.ame94.chestregistry.PlayerChest;
import ca.ame94.chestregistry.SQLite.SQLiteDatabase;

import java.io.File;
import java.sql.*;

/**
 * Provides static methods to store and query chests by player UUID or chest location on a map.
 */
public class ChestManager {

    private static String dbPath = Config.getPluginFolder() + "/chests.db";
    private static SQLiteDatabase db = new SQLiteDatabase(dbPath);

    public static void Init() {
        // make sure tables exist in database, and call methods if they don't
        try {
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create the tables used for the ChestManager
     * @return true on success
     * @throws SQLException should things go wrong
     */
    public static boolean createTables() throws SQLException {
        boolean result = false;
        int tableCount = 0;

        if (!SQLiteDatabase.tableExists(db, "chests")) {
            Logger.Info("Tables don't exist, creating...");
            if (SQLiteDatabase.createTable(db, "chests", "(x int, y int, z int, world char(128), owneruuid char(36))")) {
                result = true;
            }
        } else {
            Logger.Info("Tables exist.");
            result = true;
        }

        return result;
    }

    /**
     * Inserts a chest into the database
     * @param chest The chest
     * @return true if inserted
     */
    public static boolean insertChestEntry(PlayerChest chest) {
        boolean result = false;

        int x = chest.getLocation().getBlockX();
        int y = chest.getLocation().getBlockY();
        int z = chest.getLocation().getBlockZ();
        String world = chest.getLocation().getWorld().getName();
        String uuid = chest.getPlayerUUID().toString();

        try {
            Statement statement = db.getDB().createStatement();
            String insertStatement = "INSERT INTO chests (x, y, z, world, owneruuid) VALUES (" + x + ", " + y + ", " + z + ", '" + world + "', '" + uuid + "')";
            Logger.Info("Sending to SQL: " + insertStatement);
            statement.executeUpdate(insertStatement);
            statement.close();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * True on successful removal
     * @param chest The chest
     * @return true if removed
     */
    public static boolean removeChestEntry(PlayerChest chest) {
        boolean result = false;
        int numDeletions = 0;

        Logger.Info("removeChestEntry called");

        try {
            PreparedStatement pStatement = db.getDB().prepareStatement("DELETE FROM chests WHERE x = ? AND y = ? AND z = ?");
            pStatement.setInt(1, chest.getLocation().getBlockX());
            pStatement.setInt(2, chest.getLocation().getBlockY());
            pStatement.setInt(3, chest.getLocation().getBlockZ());
            numDeletions = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (numDeletions == 1) {
            result = true;
        } else if (numDeletions > 1) {
            Logger.Info("Removed " + numDeletions + " chests");
        }

        return result;
    }

    public static int getNumChests() {

        int result = 0;

        try {
            Statement statement = db.getDB().createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) as totalChests FROM chests");

            if (rs.next()) {
                result = rs.getInt("totalChests");
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}

package ca.ame94.chestregistry.SQLite;

import ca.ame94.chestregistry.util.Logger;

import java.sql.*;

/**
 * Creates or opens a new SQL database contained within an SQLite file
 */
public class SQLiteDatabase implements AutoCloseable {

    private Connection con = null;

    /**
     * Create / open new SQLite instance with a file path to where the db is / will go.
     * @param dbFileName The SQLite file path; typically in the plugin folder.
     * @throws RuntimeException Should something go wrong.
     */
    public SQLiteDatabase(String dbFileName) throws RuntimeException {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
            Logger.Info("Opened " + dbFileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! Could not realize SQLiteDatabase JDBC Connector Class!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! Could not establish SQLiteDatabase Conn: " + e.getMessage());
        }
    }

    /**
     * Return the DB object if needed.
     * @return The SQLite Connection object
     */
    public Connection getDB() {
        return con;
    }

    /**
     * Will close automatically
     */
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Static helper methods usable by any type of db

    /**
     * Check if table exists
     * @param db The database to use.
     * @param name name of table to check for
     * @return true if table exists
     * @throws SQLException should something go wrong
     */
    public static boolean tableExists(SQLiteDatabase db, String name) throws SQLException {
        boolean result = false;
        Connection c = db.getDB();
        Statement statement = null;

        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(name) as numInstances FROM sqlite_master WHERE type='table' AND name='" + name + "';");

            while (rs.next()) {
                int numInstances = rs.getInt("numInstances");
                if (numInstances == 1) {
                    result = true;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Could not determine if table exists: " + e.getMessage());
        }

        return result;
    }


    /**
     * Create a table
     * @param db The database to use.
     * @param tableName name of table to create
     * @param fields SQL field component: e.g.: "(id int primary key not null, name varchar(32), age int)"
     * @return true on success
     * @throws SQLException should something go wrong.
     */
    public static boolean createTable(SQLiteDatabase db, String tableName, String fields) throws SQLException {
        boolean result = false;

        Connection c = db.getDB();
        try {
            Statement statement = c.createStatement();
            statement.executeUpdate("CREATE TABLE " + tableName + " " + fields + ";");
            statement.close();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Could not create table: " + e.getMessage());
        }

        return result;
    }


}

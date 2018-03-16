package ca.ame94.chestregistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
     * Will close automatically upon gc
     */
    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

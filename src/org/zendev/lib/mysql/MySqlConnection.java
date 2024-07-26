package org.zendev.lib.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mehdi Lavasani (ZENDEV)
 * @version 3.0
 * @since 17
 */
public class MySqlConnection {
    private int port;

    private String ipAddress;
    private String username;
    private String password;
    private String databaseName;

    private MySqlConnection(MySqlConnectionBuilder builder) {
        this.port = builder.port;

        this.ipAddress = builder.ipAddress;
        this.username = builder.username;
        this.password = builder.password;
        this.databaseName = builder.databaseName;
    }

    /**
     * Creates connection string from information.
     * @return Returns the connection string it generated.
     */
    public String createConnectionString() {
        return "jdbc:mysql://" + ipAddress + ":" + port + "/" + databaseName;
    }

    /**
     * Connect to the mysql database.
     * @return Returns the connection if connection status was successful.
     * @throws ClassNotFoundException ClassNotFoundException always occurs at runtime because we are indirectly loading the class using Classloader.
     * @throws SQLException Exception and provides additional information related to failures that happen in a database context.
     */
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(createConnectionString(), username, password);
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @author Mehdi Lavasani (ZENDEV)
     * @version 3.0
     * @since 17
     */
    public static class MySqlConnectionBuilder {
        private int port;

        private String ipAddress;
        private String username;
        private String password;
        private String databaseName;

        public MySqlConnectionBuilder(String username, String password, String databaseName) {
            this.ipAddress = "127.0.0.1";
            this.port = 3306;

            this.username = username;
            this.password = password;
            this.databaseName = databaseName;
        }

        public MySqlConnectionBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public MySqlConnectionBuilder setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public MySqlConnectionBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public MySqlConnectionBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public MySqlConnectionBuilder setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public MySqlConnection build() {
            return new MySqlConnection(this);
        }
    }
}

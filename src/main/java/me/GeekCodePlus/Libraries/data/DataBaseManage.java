package me.GeekCodePlus.Libraries.data;

import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Libraries.data.MySQL.MySQL;
import me.GeekCodePlus.Libraries.data.SQLite.SQLite;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseManage {

    private static DataHead Database;

    public static Connection getConnection() throws SQLException {
        return Database.getConnection();
    }

    public DataBaseManage() {
        start();
    }

    private void start() {
        if (ConfigManage.USE_TYPE.equalsIgnoreCase("mysql")) {
            Database = new MySQL();
        } else {
            Database = new SQLite();
        }
        Database.load();
        Database.createTables();
    }

    public static void closeDatabases() {
        Database.stop();
    }


}

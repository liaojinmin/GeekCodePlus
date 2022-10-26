package me.GeekCodePlus.Libraries.data.SQLite;

import com.GeekLib.perm.zaxxer.hikari.HikariDataSource;
import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataHead;
import me.GeekCodePlus.Module.ActivationCode.Activation_DataManage;
import me.GeekCodePlus.Module.InviteCode.Invite_DataManage;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite extends DataHead {
    private static final String SETUP_A = "PRAGMA foreign_keys = ON;";
    private static final String SETUP_B = "PRAGMA encoding = 'UTF-8';";
    private static final String SQL_CREATE_1 = "CREATE TABLE IF NOT EXISTS `geek_invite_data` ( " +
            "`id` integer PRIMARY KEY , " +
            "`owner_name` VARCHAR(255) NOT NULL UNIQUE , " +
            "`owner_uuid` CHAR(36) NOT NULL UNIQUE , " +
            "`owner_cdk` longtext NOT NULL , " +
            "`owner_count` INT(80) NOT NULL DEFAULT '0' , " +
            "`owner_reward` INT(80) NOT NULL DEFAULT '0');";
    private static final String SQL_CREATE_2 = "CREATE TABLE IF NOT EXISTS `geek_invite_user_data` ( " +
            "`id` integer PRIMARY KEY , " +
            "`user_name` VARCHAR(255) NOT NULL , " +
            "`user_uuid` CHAR(36) NOT NULL , " +
            "`user_cdk` VARCHAR(80) NOT NULL , " +
            "`user_ip` VARCHAR(80) NOT NULL);";
    private static final String SQL_CREATE_3 = "CREATE TABLE IF NOT EXISTS `geek_activation_data` ( " +
            "`id` integer PRIMARY KEY , " +
            "`code` VARCHAR(255) NOT NULL UNIQUE , " +
            "`lave` INT(36) NOT NULL , " +
            "`command` longtext NOT NULL , " +
            "`perm` VARCHAR(255) NOT NULL)";
    private static final String SQL_CREATE_4 = "CREATE TABLE IF NOT EXISTS `geek_activation_user_data` ( " +
            "`id` integer PRIMARY KEY , " +
            "`name` VARCHAR(255) NOT NULL , " +
            "`code` CHAR(80) NOT NULL , " +
            "`command` longtext NOT NULL);";
    private static final String SQL_CREATE_5 = "CREATE TABLE IF NOT EXISTS `geek_share_data` ( " +
            "`id` integer PRIMARY KEY, " +
            //服务器名称
            "`server_name` VARCHAR(255) NOT NULL, " +
            "`item_stack` longtext NOT NULL, " +
            //物品
            "`item_name` longtext NOT NULL, " +
            "`item_amount` INT NOT NULL, " +
            //cdk
            "`code` VARCHAR(24) NOT NULL UNIQUE, " +
            //上传时间
            "`upload_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            //上传者名字
            "`upload_playname` VARCHAR(36) NOT NULL, " +
            //使用状态
            "`use_status` VARCHAR(20) NOT NULL, " +
            //使用者用户名
            "`use_playname` VARCHAR(36) NOT NULL DEFAULT 'null', " +
            //使用时间
            "`use_time` VARCHAR(36) NOT NULL DEFAULT 'null');";
    private static HikariDataSource SqliteDataSource;


    public SQLite() {
        super();
    }

    @Override
    public void load() {
        final String SqliteUrl = "jdbc:sqlite:"+ ConfigManage.getDataFolder()+"/Geek-Data.db";
        SqliteDataSource = new HikariDataSource();
        SqliteDataSource.setDataSourceClassName("org.sqlite.SQLiteDataSource");
        SqliteDataSource.addDataSourceProperty("url", SqliteUrl);
        //附件参数
        SqliteDataSource.setMaximumPoolSize(ConfigManage.MAXIMUM_POOL_SIZE);
        SqliteDataSource.setMinimumIdle(ConfigManage.MINIMUM_IDLE);
        SqliteDataSource.setMaxLifetime(ConfigManage.MAXIMUM_LIFETIME);
        SqliteDataSource.setKeepaliveTime(ConfigManage.KEEPALIVE_TIME);
        SqliteDataSource.setConnectionTimeout(ConfigManage.CONNECTION_TIMEOUT);
        SqliteDataSource.setIdleTimeout(ConfigManage.IDLE_TIMEOUT);
        SqliteDataSource.setPoolName("GeekCodePlus-SQLITE");
       // create();
    }

    @Override
    public void createTables() {
        try (Connection connection = SqliteDataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(SETUP_A);
                statement.execute(SETUP_B);
                statement.execute(SQL_CREATE_1);
                statement.execute(SQL_CREATE_2);
                statement.execute(SQL_CREATE_3);
                statement.execute(SQL_CREATE_4);
                statement.execute(SQL_CREATE_5);
            }
        } catch (SQLException e) {
            GeekCodeMain.say("§c 创建数据库表时出错，你使用了正确的版本吗?-InviteCode");
            GeekCodeMain.say("§c SQLITE err err?-InviteCode");
            Bukkit.getPluginManager().disablePlugin(GeekCodeMain.instance);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return SqliteDataSource.getConnection();
    }

    //关闭链接
    @Override
    public void stop() {
        if (SqliteDataSource != null) {
            SqliteDataSource.close();
        }
    }
}

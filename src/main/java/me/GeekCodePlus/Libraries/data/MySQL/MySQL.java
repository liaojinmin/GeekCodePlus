package me.GeekCodePlus.Libraries.data.MySQL;

import com.zaxxer.hikari.HikariDataSource;
import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.LibrariesManage;
import me.GeekCodePlus.Libraries.data.DataHead;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class MySQL extends DataHead {

    private static final String SQL_CREATE_1 = "CREATE TABLE IF NOT EXISTS `geek_invite_data` ( " +
            "`id` INT(80) NOT NULL AUTO_INCREMENT , " +
            "`owner_name` VARCHAR(255) NOT NULL UNIQUE , " +
            "`owner_uuid` CHAR(36) NOT NULL UNIQUE , " +
            "`owner_cdk` longtext NOT NULL , " +
            "`owner_count` INT(80) NOT NULL DEFAULT '0' , " +
            "`owner_reward` INT(80) NOT NULL DEFAULT '0', " +
            "`crafttime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , " +
            "`updatatime` TIMESTAMP on update CURRENT_TIMESTAMP NULL , " +
            "PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static final String SQL_CREATE_2 = "CREATE TABLE IF NOT EXISTS `geek_invite_user_data` ( " +
            "`id` INT(80) NOT NULL AUTO_INCREMENT , " +
            "`user_name` VARCHAR(255) NOT NULL , " +
            "`user_uuid` CHAR(36) NOT NULL , " +
            "`user_cdk` VARCHAR(80) NOT NULL , " +
            "`user_ip` VARCHAR(80) NOT NULL , " +
            "`crafttime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , " +
            "`updatatime` TIMESTAMP on update CURRENT_TIMESTAMP NULL , " +
            "PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static final String SQL_CREATE_3 = "CREATE TABLE IF NOT EXISTS `geek_activation_data` ( " +
            "`id` INT(80) NOT NULL AUTO_INCREMENT , " +
            "`code` VARCHAR(255) NOT NULL UNIQUE , `" +
            "lave` INT(36) NOT NULL , " +
            "`command` longtext NOT NULL , " +
            "`perm` VARCHAR(255) NOT NULL , " +
            "`crafttime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , " +
            "`updatatime` TIMESTAMP on update CURRENT_TIMESTAMP NULL , " +
            "PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static final String SQL_CREATE_4 = "CREATE TABLE IF NOT EXISTS `geek_activation_user_data` ( " +
            "`id` INT(80) NOT NULL AUTO_INCREMENT , " +
            "`name` VARCHAR(255) NOT NULL , " +
            "`code` CHAR(80) NOT NULL , " +
            "`command` longtext NOT NULL , " +
            "`crafttime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , " +
            "`updatatime` TIMESTAMP on update CURRENT_TIMESTAMP NULL , " +
            "PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static final String SQL_CREATE_5 = "CREATE TABLE IF NOT EXISTS `geek_share_data` ( " +
            "`id` INT(80) NOT NULL AUTO_INCREMENT, " +
            //???????????????
            "`server_name` VARCHAR(255) NOT NULL, " +
            "`item_stack` longtext NOT NULL, " +
            //??????
            "`item_name` longtext NOT NULL, " +
            "`item_amount` INT NOT NULL, " +
            //cdk
            "`code` VARCHAR(24) NOT NULL UNIQUE, " +
            //????????????
            "`upload_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            //???????????????
            "`upload_playname` VARCHAR(36) NOT NULL, " +
            //????????????
            "`use_status` VARCHAR(20) NOT NULL, " +
            //??????????????????
            "`use_playname` VARCHAR(36) NOT NULL DEFAULT 'null', " +
            //????????????
            "`use_time` VARCHAR(36) NOT NULL DEFAULT 'null', " +
            "PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private static HikariDataSource MysqlDataSource;

    public MySQL() {
        super();
    }

    @Override
    public void load() {
        // ???????????? HikariCP ?????????
        final String url = "jdbc:mysql://" + ConfigManage.HOST + ":" + ConfigManage.PORT + "/" + ConfigManage.DATA_BASE + ConfigManage.PARAMS;
        MysqlDataSource = new HikariDataSource();
        MysqlDataSource.setJdbcUrl(url);
        MysqlDataSource.setUsername(ConfigManage.USER_NAME);
        MysqlDataSource.setPassword(ConfigManage.PASS_WORD);
        // ???????????????????????????
        if (LibrariesManage.isServer(Bukkit.getBukkitVersion().substring(0, Bukkit.getBukkitVersion().indexOf("-")))) {
            MysqlDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        } else {
            MysqlDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        }
        MysqlDataSource.setMaximumPoolSize(ConfigManage.MAXIMUM_POOL_SIZE);
        MysqlDataSource.setMinimumIdle(ConfigManage.MINIMUM_IDLE);
        MysqlDataSource.setMaxLifetime(ConfigManage.MAXIMUM_LIFETIME);
        MysqlDataSource.setKeepaliveTime(ConfigManage.KEEPALIVE_TIME);
        MysqlDataSource.setConnectionTimeout(ConfigManage.CONNECTION_TIMEOUT);
        MysqlDataSource.setIdleTimeout(ConfigManage.IDLE_TIMEOUT);
        MysqlDataSource.setPoolName("GeekCodePlus-MYSQL");
    }

    @Override
    public void createTables() {
        String[] sqlArray = {SQL_CREATE_1, SQL_CREATE_2, SQL_CREATE_3, SQL_CREATE_4, SQL_CREATE_5};
        try (Connection connection = MysqlDataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                for (String sql : sqlArray) {
                    statement.addBatch(sql);
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            GeekCodeMain.say("??c?????????????????????????????????????????????????????????????-InviteCode");
            GeekCodeMain.say("??cMYSQL err err?-InviteCode");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(GeekCodeMain.instance);
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return MysqlDataSource.getConnection();
    }

    @Override
    public void stop() {
        if (MysqlDataSource != null) {
            MysqlDataSource.close();
        }
    }

}

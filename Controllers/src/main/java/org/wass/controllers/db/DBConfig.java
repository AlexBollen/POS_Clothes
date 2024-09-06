/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers.db;

import static org.wass.models.db.DBConnection.*;

/**
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DBConfig {
    
    private static DBConfig DB_CONFIG;
    public static DBConfig nDataBaseConfig() {
        if (DB_CONFIG == null) {
            DB_CONFIG = new DBConfig();
            DB_CONFIG.set(DataConfig.MySQLServerPort, null);
            DB_CONFIG.set(DataConfig.MySQLServer, null);
        }
        return DB_CONFIG;
    }
    
    public void set(DataConfig config, String value) {    
        String nVal = value;
        if (nVal == null) {
            nVal = config.getDefValue();
        }
        System.setProperty(config.getName(), nVal);
    }
    
    public String get(DataConfig config) {
        return System.getProperty(config.getName(), config.getDefValue());
    }
    
    public static enum DataConfig {
        MySQLDataBase(JDBC_DATABASE),
        MySQLUserName(JDBC_DATABASE_NAME),
        MySQLUserPassword(JDBC_DATABASE_PASSWORD),
        
        MySQLServerPort(JDBC_PORT, "3306"),
        MySQLServer(JDBC_IP, "localhost");
        
        private final String name;
        private final String defValue;

        private DataConfig(String name) {
            this(name, null);
        }
        
        private DataConfig(String name, String defValue) {
            this.name = name;
            this.defValue = defValue;
        }

        public String getName() {
            return name;
        }

        public String getDefValue() {
            return defValue;
        }
    }
}

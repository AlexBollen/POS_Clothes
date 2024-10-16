/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers.db;

import static org.wass.models.db.DBConnection.*;

/**
 * Clase encargado de gestionar las configuraciones para la conexión de la DB.
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DBConfig {

    /** Instancia del objeto de configuración. */
    private static DBConfig DB_CONFIG;

    /**
     * Método preteneciente a la clase encargado de devolver una instancia o
     * crearla si es la primera llamada.
     *
     * @return objeto configuración
     */
    public static DBConfig nDataBaseConfig() {
        if (DB_CONFIG == null) {
            DB_CONFIG = new DBConfig();
            DB_CONFIG.set(DataConfig.MySQLServerPort, null);
            DB_CONFIG.set(DataConfig.MySQLServer, null);
        }
        return DB_CONFIG;
    }

    /**
     * Establece una nueva propiedad (valor) para la configuración de conexión.
     * @param config tipo de configuración
     * @param value el nuevo valor
     */
    public void set(DataConfig config, String value) {
        String nVal = value;
        if (nVal == null) {
            nVal = config.getDefValue();
        }
        System.setProperty(config.getName(), nVal);
    }

    /**
     * Recupera el valor de una configuración de la conexión.
     * @param config tipo de configuración
     * @return el valor
     */
    public String get(DataConfig config) {
        return System.getProperty(config.getName(), config.getDefValue());
    }

    /**
     * Clase enumerada encargado de definir los tipos de configuración que se
     * pueden operar al realizar la conexión a la base de datos.
     */
    public static enum DataConfig {
        /** Conf: Nombre de la base de datos. */
        MySQLDataBase(JDBC_DATABASE),
        /** Conf: Nombre de usuario (MySQL). */
        MySQLUserName(JDBC_DATABASE_NAME),
        /** Conf: Contraseña del usuario (MySQL). */
        MySQLUserPassword(JDBC_DATABASE_PASSWORD),

        /** Conf: El puerto donde trabaja el servidor DB (MySQL). */
        MySQLServerPort(JDBC_PORT, "3306"),
        /** Conf: La direccion IP del servidor (MySQL). */
        MySQLServer(JDBC_IP, "localhost");

        /** Nombre clave de la configuración. */
        private final String name;
        /** Valor predeterminado de la configuración (opcional). */
        private final String defValue;

        /**
         * Constructor de la clase enumerda <code>DataConfig</code> donde se
         * inicializan las propiedades de forma predeterminada.
         *
         * @param name nombre clave
         */
        private DataConfig(String name) {
            this(name, null);
        }

        /**
         * Constructor de la clase enumerda <code>DataConfig</code> donde se
         * inicializan las propiedades de forma predeterminada.
         *
         * @param name nombre clave
         * @param defValue valor predeterminado
         */
        private DataConfig(String name, String defValue) {
            this.name = name;
            this.defValue = defValue;
        }

        /* (non-Javadoc)*/
        public String getName() { return name; }
        /* (non-Javadoc )*/
        public String getDefValue() { return defValue; }
    }
}

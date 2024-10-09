/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * Clase <code>DBConnection</code> encargado de gestionar un {@link javax.sql.DataSource}
 * para las conexiones SQL a travez de <b>HikariCP</b>.
 * <p>
 * La configuración se establece medinate las propiedades el sistemas:<pre><code>
 *      System.setProperty(DBConnection.JDBC_DATABASE, "myDataBase");
 * </code></pre>
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DBConnection {

    /** Logger de la clase. */
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    /** URL de conexión para la base de datos MySQL. */
    private static final String JDBC_CONNECTION = "jdbc:mysql://%s:%s/%s";

    /** Clave JDBC para el nombre de la base de dato. */
    public static final String JDBC_DATABASE            = "jdbc.database";
    /** Clave JDBC para el nombre de usuario sel servidor MySQL */
    public static final String JDBC_DATABASE_NAME       = "jdbc.database.username";
    /** Clave JDBC para el la contraseña sel servidor MySQL */
    public static final String JDBC_DATABASE_PASSWORD   = "jdbc.database.password";
    /** Clave JDBC para el IP del servidor. */
    public static final String JDBC_IP                  = "jdbc.ip";
    /** Clave JDBC para el puerto utilizado por el servidor.*/
    public static final String JDBC_PORT                = "jdbc.port";

    //--------------------------------------------------------------------------
    //                          Hikari - DataSource
    //--------------------------------------------------------------------------    
    /** Configuraciones para la API {@code HikariCP}. */
    private static final HikariConfig JDB_CONFIG = new HikariConfig();
    /** Un {@link javax.sql.DataSource} utilizando Hikari. */
    private static HikariDataSource DS;

    /*
        Bloque de código donde se inicializa las propiedades del DataSource que
        se estará utilizando.
    */
    static {
        Properties properties = System.getProperties();
        JDB_CONFIG.setJdbcUrl(String.format(JDBC_CONNECTION,
                Objects.requireNonNull(
                        properties.getProperty(JDBC_IP, null), "IP inválido."),
                Objects.requireNonNull(
                        properties.getProperty(JDBC_PORT, null), "Puerto inválido."),
                Objects.requireNonNull(
                        properties.getProperty(JDBC_DATABASE, null), "Base de datos no encotrado.")
        ));
        JDB_CONFIG.setUsername(Objects.requireNonNull(properties.getProperty(JDBC_DATABASE_NAME, null), "Nombre de usuario jdbc inválido"));
        JDB_CONFIG.setPassword(Objects.requireNonNull(properties.getProperty(JDBC_DATABASE_PASSWORD, null), "Contraseña jdbc inválido"));

        JDB_CONFIG.addDataSourceProperty("cachePrepStmts" , "true");
        JDB_CONFIG.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        JDB_CONFIG.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        DS = new HikariDataSource( JDB_CONFIG );

        LOGGER.log(Level.INFO, "JDBC inicializada...");
    }

    /** Constructor privado. */
    private DBConnection() {}

    /**
     * Método encargado de devolver un {@link javax.sql.DataSource}.
     * @return Un objeto {@link javax.sql.DataSource}
     */
    public static DataSource getDataSource() {
        return DS;
    }
}

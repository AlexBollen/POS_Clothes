/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.wass.controllers.ControllerException;
import org.wass.models.db.DBConnection;

/**
 * Clase <code>DataBase</code> encargado de proporcionar una conexión con un
 * {@link javax.sql.DataSource} personalizado capacitado de soportar multiples
 * peticiones sin sobrecargar el servidor de la base de datos.
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DataBase {
    
    /** Implementacion del objeto {@link org.wass.controllers.db.DataBase}. */
    private static DataBase DB;    
    /* Devuevle una instancia del objeto {@link org.wass.controllers.db.DataBase}. */
    public static DataBase nDataBase() {
        if (DataBase.DB == null) {
            DataBase.DB = new DataBase();
        }
        return DataBase.DB;
    }
    
    /** Constructor privvado. */
    private DataBase() { }    
    /* Devuelve una {@link javax.sql.DataSource}. */
    public DataSource getDataSource() {
        return DBConnection.getDataSource();
    }
    
    /**
     * Método encargado de devolver una conexión del {@link javax.sql.DataSource}.
     * @return conexión db
     * 
     * @throws ControllerException si sucede un error al obtener la conexión hacia
     *                              la base de datos
     */
    public Connection getConnection() throws ControllerException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new ControllerException("Error al obtener una conexión: " + e.getMessage(), e);
        }
    }
}

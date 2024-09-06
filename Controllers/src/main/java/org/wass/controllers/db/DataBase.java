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
 *
 * @author wil
 */
public final class DataBase {
    
    private static DataBase DB;
    public static DataBase nDataBase() {
        if (DataBase.DB == null) {
            DataBase.DB = new DataBase();
        }
        return DataBase.DB;
    }
        
    private DataBase() { }
    
    public DataSource getDataSource() {
        return DBConnection.getDataSource();
    }
    
    public Connection getConnection() throws ControllerException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new ControllerException("Error al obtener una conexión: " + e.getMessage(), e);
        }
    }
}

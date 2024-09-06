/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import java.sql.SQLException;
import org.wass.controllers.ControllerException;
import org.wass.controllers.db.DBConfig;
import static org.wass.controllers.db.DBConfig.*;
import static org.wass.controllers.db.DataBase.*;

/**
 * Clase principal encargada de gestionar la entrada y salida de aplicación 
 * <b>POS Clothes</b>, este es el punto de montaje del software en donde solo se
 * llaman los objetos principales de partida.
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class App {
    
    /**
     * El método principal; utiliza cero argumentos en el arreglo args.
     * @param args argumentos de la línea de comando
     */
    public static void main(String[] args) {        
        DBConfig config = nDataBaseConfig();
        config.set(DataConfig.MySQLDataBase, "test");
        config.set(DataConfig.MySQLUserName, "root");
        config.set(DataConfig.MySQLUserPassword, "phpacceso");
        
        try {
            try (var connection = nDataBase().getConnection()) {
                System.out.println("[ ok ] Conexión establecida con: " + nDataBaseConfig().get(DataConfig.MySQLServer));
            }
        } catch (SQLException | ControllerException ex) {
            ex.printStackTrace(System.err);
        }
    }
}

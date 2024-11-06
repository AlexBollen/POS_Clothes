/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import java.io.File;
import javax.swing.JFrame;
import org.wass.controllers.db.DBConfig;

/**
 * @author wil
 */
public final class Sistema {
    
    // === ----------------------------------------------------------------- ===
    // ===                          RUTAS - CACHÉ
    // === ----------------------------------------------------------------- ===
    /** Rura principal de usuario. */
    public static final String RUTA_PRINCIPAL;
    /** Directorio de configuración (cache). */
    public static final File RUTA_CACHE;
    
    /** Clave del sistema. */
    public static final String LLAVE;
    
    /** Datos de caché de la aplicación. */
    private static final Cache CACHE;
    
    static {
        // rutas para la caché
        RUTA_PRINCIPAL = System.getProperty("user.home");
        RUTA_CACHE = new File(RUTA_PRINCIPAL, ".POS_Clothes");
        
        // llave
        LLAVE = "POSBar1234567890"; // 128 bit
        
        // nueva caché
        CACHE = new Cache();
    }
    
    public static void check() {
        if (RUTA_CACHE.exists()) {
            return;
        }
        
        CACHE.cargarCache();
        FormConfig config = new FormConfig(new JFrame(), true);
        config.setVisible(true);
        if (config.getReturnStatus() == FormConfig.RET_OK) {
            Cache.Conf cacheDB = getCache().getConfiguracion(Cache.CACHE_BASE_DATOS);
            cacheDB.setDato(DBConfig.DataConfig.MySQLDataBase.getName(), config.getAlmacen());
            cacheDB.setDato(DBConfig.DataConfig.MySQLServer.getName(), config.getServidor());
            cacheDB.setDato(DBConfig.DataConfig.MySQLServerPort.getName(), config.getPuerto());
            cacheDB.setDato(DBConfig.DataConfig.MySQLUserName.getName(), config.getUsuario());
            cacheDB.setDato(DBConfig.DataConfig.MySQLUserPassword.getName(), config.getClave());
            getCache().guardarCache();
            
            config.dispose();
        } else {
            config.dispose();
            System.exit(0);
        }
        
        CACHE.cargarCache();
    }
    public static Cache getCache() {
        return CACHE;
    }
}

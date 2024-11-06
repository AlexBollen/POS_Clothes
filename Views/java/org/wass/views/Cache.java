/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.wass.models.utilities.CipherUtilities;

/**
 * @author wil
 */
public final class Cache {
    
    // === ----------------------------------------------------------------- ===
    // ===                  CLAVES - FICHERO CACHÉ
    // === ----------------------------------------------------------------- ===
    public static final String CACHE_APLICACION = "{app}";
    public static final String CACHE_BASE_DATOS = "{mysql}";
    
    // === ----------------------------------------------------------------- ===
    // ===                 CLAVES - CONFIGURACIÓN [APP]
    // === ----------------------------------------------------------------- ===
    public static final String WIN_MAXIMIZED_BOTH = "win.pantalla.full";
    
    
    // === ----------------------------------------------------------------- ===
    // ===                  CLAVES - CONFIGURACIÓN [DB]
    // === ----------------------------------------------------------------- ===
    private static final String CONF_APLICACION = "POS_Clothes.json";
    private static final String CONF_MYSQL      = "POS_MySQL.json";
    
    private static final Logger LOGGER = Logger.getLogger(Cache.class.getName());

    public static class Conf {
        
        private String fichero;
        private JSONObject datos;
        
        private boolean cifrado;

        public Conf(String fichero, boolean cifrado) {
            this.datos = new JSONObject();
            this.fichero = fichero;
            this.cifrado = cifrado;
        }
        
        public <T> void setDato(String name, T dato) {
            if (cifrado) {
                if (!(dato instanceof String)) {
                    throw new IllegalArgumentException("El argumento tiene que ser de tipo String.");
                }       
                byte[] iv = new byte[Sistema.LLAVE.length()];
                datos.put(name, CipherUtilities.encriptar(Sistema.LLAVE, iv, (String) dato));
            } else {
                datos.put(name, dato);
            }
        }
        
        public int getInt(String name, int def) {
            return datos.optInt(name, def);
        }
        public String getString(String name, String defVal) {            
            if (cifrado && datos.has(name)) {
                byte[] iv = new byte[Sistema.LLAVE.length()];
                String crudo = datos.getString(name);
                
                return CipherUtilities.decriptar(Sistema.LLAVE, iv, crudo);
            } else {
                return datos.optString(name, defVal);
            }
        }

        final void setDatos(JSONObject datos) {
            this.datos = datos;
        }
        
        final String getFichero() {
            return fichero;
        }

        final JSONObject getDatos() {
            return datos;
        }        
    }
    
    private Map<String, Conf> configuraciones;
    Cache() {
        this.configuraciones = new HashMap<>();        
        this.init();
    }
    
    public Conf getConfiguracion(String idconf) {
        return configuraciones.get(idconf);
    } 
    
    private void init() {
        configuraciones.put(CACHE_APLICACION, new Conf(CONF_APLICACION, false));
        configuraciones.put(CACHE_BASE_DATOS, new Conf(CONF_MYSQL, true));
    }

    public void cargarCache() {
        StringBuilder buff = new StringBuilder();
        for (Map.Entry<?, Conf> entry : this.configuraciones.entrySet()) {
            leerCache(entry.getValue());
            
            buff.append('\n')
                .append(" * Configuración ->")
                .append(entry.getKey())
                .append(" : ")
                .append(entry.getValue().getFichero());
        }
        
        LOGGER.log(Level.INFO, "Cach\u00e9 cargado: {0}", buff);
    }
    
    public void guardarCache() {
        StringBuilder buff = new StringBuilder();
        for (Map.Entry<?, Conf> entry : this.configuraciones.entrySet()) {
            escribirCache(entry.getValue());
            
            buff.append('\n')
                .append(" * Configuración ->")
                .append(entry.getKey())
                .append(" : ")
                .append(entry.getValue().getFichero());
        }
        
        LOGGER.log(Level.INFO, "Cach\u00e9 guardada: {0}", buff);
    }
    
    private void leerCache(Conf configuracion) {        
        InputStream in = null;
        boolean init;
        try {
            File fconf = new File(Sistema.RUTA_CACHE, configuracion.getFichero());
            if (! verificar(Sistema.RUTA_CACHE, true)) {
                throw new IOException("No se puede crear el directorio: " + Sistema.RUTA_CACHE);
            }
            
            init = !fconf.exists();
            if (! verificar(fconf, false)) {
                throw new IOException("No se puede crear el fichero: " + fconf);
            }
            
            in = new FileInputStream(fconf);
            configuracion.setDatos(init 
                    ? new JSONObject() 
                    : new JSONObject(new JSONTokener(in)));
        } catch (IOException | JSONException e) {
            configuracion.setDatos(new JSONObject());
            LOGGER.log(Level.WARNING, "Error al leer el cach\u00e9: {0}", e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    /* ignorar */
                }
            }
        }
    }
    
    private void escribirCache(Conf configuracion) {
        Writer writer = null;
        try {
            File fconf = new File(Sistema.RUTA_CACHE, configuracion.getFichero());
            writer = new FileWriter(fconf);
            
            configuracion.getDatos().write(writer);
        } catch (IOException | JSONException e) {
            configuracion.setDatos(new JSONObject());
            LOGGER.log(Level.WARNING, "Error al leer el cach\u00e9: {0}", e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    /* ignorar */
                }
            }
        }
    }
    
    private boolean verificar(File file, boolean dir) throws IOException {
        if (! file.exists()) {
            if (dir) {
                return file.mkdirs();
            } else {
                return file.createNewFile();
            }
        }
        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.views.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Clase de métodos para cargar y registrar
 * fuentes en el sistema
 * 
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomFont {
    // Método para cargar y registrar un listado de fuentes
    public static void cargarFuentes(String fontsPath) {
        Properties props = new Properties();
        try (InputStream is = CustomFont.class.getResourceAsStream(fontsPath + "roboto-fonts.properties")) {
            props.load(is);
            for (String fontName : props.stringPropertyNames()) {
                registrarFuente(fontsPath + fontName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para registrar una fuente
    public static void registrarFuente(String path) {
        try (InputStream is = CustomFont.class.getResourceAsStream(path)) {
            if (is == null) {
                System.err.println("No se encontró la fuente: " + path);
                return;
            }
            // Cargar la fuente desde el archivo
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, is);
            // Registrar la fuente en el sistema
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}

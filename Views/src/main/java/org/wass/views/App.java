/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import org.wass.views.main.Main;

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
        System.out.println("Hola MVC!");
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Main().setVisible(true);
            }
        });
        
    }
}

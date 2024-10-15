/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.listeners;

/**
 * Una interfaz <code>OyenteConfiguracion</code> es el encargado de responder los
 * eventos que realizan o se activan en el menú de configuraciones.
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OyenteConfiguracion {
    
    /** Opciones - Menu*/
    public static enum Opcion {
        Usuario,
        Aplicacion;
    }
    
    /**
     * Método encargado de activar un evento en respuesta a este oyente.
     * @param op opción
     */
    public void opcion(Opcion op);
}

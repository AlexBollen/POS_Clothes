/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers;

/**
 * Una clase personalizada para las excepciones (errores) en tiempo de ejecución
 * (procesos).
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.o
 */
public class ControllerException extends RuntimeException {

    /**
     * Contructor de la clase <code>ControllerException</code>.
     * 
     * @param message mensage del error
     * @param cause causante
     */
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}

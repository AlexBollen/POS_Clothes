/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.listeners;

/**
 * @author wil
 */
public interface HeaderCRUDListener {
    
    public static final String LK_NUEVO  = "Nuevo";
    public static final String LK_EDITAR = "Editar";
    public static final String LK_LISTAR = "Listar";
    
    public void nuevo();
    
    public void editar();
    
    public void listar();
}

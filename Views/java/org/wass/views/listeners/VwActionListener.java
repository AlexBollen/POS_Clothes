package org.wass.views.listeners;

/**
 * Innterfaz encargado de gestionar los oyentes de un {@link org.wass.views.component.VwHeader}
 * para cade botón de ello.
 * 
 * @see  org.wass.views.component.VwHeader
 * 
 * @author wil
 */
public interface VwActionListener {
    
    /**
     * Accion a realizar
     * 
     * @see  org.wass.views.component.VwHeader#BUTTON_LIST
     * @see  org.wass.views.component.VwHeader#BUTTON_NEW
     * 
     * @param target tipo de accion
     */
    void doAction(int target);
}

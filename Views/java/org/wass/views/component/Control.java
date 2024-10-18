/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.component;

import javax.swing.JComponent;

/**
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Control {
    
    public String getComponenteTitle();
    
    public default String getComponenteDescribe() {
        return null;
    }
    
    public default Footer getFooterComponent() {
        return null;
    }
    
    public default JComponent getHeaderComponent() {
        return null;
    }
}

/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import org.wass.views.listeners.FooterCRUDListener;

/**
 * @author wil
 */
public class Footer extends AbstractCardPanel  {
    
    private final List<FooterCRUDListener> crudListeners;
    
    public Footer() {
        crudListeners = new ArrayList<>();
        
        setBackground(new Color(237, 237, 235));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(204, 204, 204)));
    }
    
    public void addFooterCRUDListener(FooterCRUDListener listener) {
        crudListeners.add(listener);
    }
    
    public void removeFooterCRUDListener(FooterCRUDListener listener) {
        crudListeners.remove(listener);
    }
    
    protected final void fireFooterCRUDListener(boolean save, boolean cancel) {
        for (final FooterCRUDListener l : crudListeners) {
            if (l != null) {
                if (save) {
                    l.guardar();
                }
                if (cancel) {
                    l.cancelar();
                }
            }
        }
    }
}

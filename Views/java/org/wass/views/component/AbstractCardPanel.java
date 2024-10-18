/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.component;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author wil
 */
public abstract class AbstractCardPanel extends JPanel {
    
    private CardLayout layout;
    private String card;
    
    public AbstractCardPanel() {
        layout = new CardLayout();
        setLayout(layout);
    }
    
    public void addViewComponent(String name, Component component) {
        if (card == null) {
            card = name;
        }
        add(component, name);
    }
    
    public void showViewComponent(String name) {
        layout.show(this, name);
    }

    public String getCard() {
        return card;
    }
}

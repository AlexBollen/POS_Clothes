package org.wass.views.component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.wass.views.listeners.VwActionListener;

/**
 * @author wil
 */
public class AbstractView extends JPanel {
    
    public static final String VC_LISTAR        = "VC_LISTAR";
    public static final String VC_ACTUALIZAR    = "VC_ACTUALIZAR";
    public static final String VC_AGREGAR       = "VC_AGREGAR";
    
    private Component header;
    private Component footer;
    
    private JComponent view;
    private String card;
    protected CardLayout layout;
    
    private List<VwActionListener> actionListeners = new ArrayList<>();
    
    public AbstractView() {
        initComponents();
        layout = new CardLayout();
    }

    private void fireVwActionListener() {
        for (final VwActionListener val : actionListeners) {
            val.doAction(0);
        }
    }
    
    public void addVwActionListener(VwActionListener val) {
        actionListeners.add(val);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jPanelFooter = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanelHeader.setLayout(new java.awt.BorderLayout());
        add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jPanelFooter.setLayout(new java.awt.BorderLayout());
        add(jPanelFooter, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    public void setHeader(Component header) {
        this.header = header;
        this.jPanelHeader.add(header, BorderLayout.CENTER);
    }

    public void setFooter(Component footer) {
        this.footer = footer;
        this.jPanelFooter.add(footer, BorderLayout.CENTER);
    }

    public void setView(JComponent view) {
        this.view = view;
        this.view.setLayout(layout);
        this.add(view, BorderLayout.CENTER);
    }

    public void addViewComponent(String name, Component component) {
        if (card == null) {
            card = name;
        }
        view.add(component, name);
        fireVwActionListener();
    }

    public String getCurrentCard() {
        return card;
    }
    
    public void showViewComponent(String name) {        
        card = name;
        layout.show(view, name);
        fireVwActionListener();
    }
    
    public Component getHeader() {
        return header;
    }

    public Component getFooter() {
        return footer;
    }

    public Component getView() {
        return view;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelHeader;
    // End of variables declaration//GEN-END:variables
}

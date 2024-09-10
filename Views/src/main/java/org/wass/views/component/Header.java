/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.wass.views.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author marco
 */
public class Header extends javax.swing.JPanel {

    /**
     * Creates new form Header
     */
    public Header() {
        
        initComponents();
        setOpaque(false);
    }

   
     @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setPaint(new GradientPaint(0, 0, new Color(94, 142, 153), 0, getHeight(), new Color(94, 142, 153)));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    public void setLabelText(String newText) {
        jLabelVista.setText(newText);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelVista = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(230, 230, 230));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoPetit.png"))); // NOI18N
        jLabel1.setText("WASS");

        jLabelVista.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelVista.setForeground(new java.awt.Color(230, 230, 230));
        jLabelVista.setText("Vista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(95, 95, 95)
                .addComponent(jLabelVista)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelVista))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelVista;
    // End of variables declaration//GEN-END:variables
}

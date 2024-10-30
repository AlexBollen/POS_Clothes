/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.wass.views.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.wass.views.listeners.VwActionListener;

/**
 * @author marco
 */
public class Header extends JPanel {

    private VwActionListener clonseListener;
    
    public Header() {        
        initComponents();
        setOpaque(false);
    }

    public VwActionListener getClonseListener() {
        return clonseListener;
    }

    public void setClonseListener(VwActionListener clonseListener) {
        this.clonseListener = clonseListener;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(244, 244, 244));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    public void setUserName(String value) {
        jLabelUserName.setText(value);
    }
    
    public void setUserRol(String value) {
        jLabelRol.setText(value);
    }
    
    public void setHeaderTitle(String value) {
        jLabelTitle.setText(value);
    }
    
    public void setProfile(Image image) {
        jLabelProfile.setIcon(new ImageIcon(image.getScaledInstance(48, 48, Image.SCALE_SMOOTH)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUserName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelProfile = new javax.swing.JLabel();
        jLabelRol = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 51, 51));

        jLabelUserName.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jLabelUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelUserName.setText("Nombre usuario");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logout_box.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/avatardefault.png"))); // NOI18N

        jLabelRol.setForeground(new java.awt.Color(133, 133, 133));
        jLabelRol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelRol.setText("Mi rolModel");

        jLabelTitle.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabelTitle.setText("My Panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jLabelRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelRol)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (clonseListener != null) {
            clonseListener.doAction(1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelProfile;
    private javax.swing.JLabel jLabelRol;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUserName;
    // End of variables declaration//GEN-END:variables
}

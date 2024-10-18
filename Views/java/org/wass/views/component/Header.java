/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.wass.views.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author marco
 */
public class Header extends JPanel {

    /**
     * Creates new form Header
     */
    public Header() {        
        initComponents();
        jLabelDescripcion.setVisible(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(244, 244, 244));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    public void setDescripcion(String desc) {
        if (desc == null || desc.isEmpty()) {
            jLabelDescripcion.setVisible(false);
        } else {
            if (!jLabelDescripcion.isVisible()) {
                jLabelDescripcion.setVisible(true);
            }
            jLabelDescripcion.setText("    " + desc);
        }
    }
    
    public void setHeaderView(JComponent component) {
        if (jPanelHeaderView.getComponentCount() > 0) {
            jPanelHeaderView.removeAll();
        }
        if (component != null) {
            jPanelHeaderView.add(component, BorderLayout.CENTER);
        }
    }
    
    public void setUserName(String value) {
        jLabelUserName.setText(value);
    }
    
    public void setUserRol(String value) {
        jLabelRol.setText(value);
    }
    
    public void setHeaderTitle(String value) {
        jLabelTitle.setText(" " + value);
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
        jPanelHeaderView = new javax.swing.JPanel();
        jLabelDescripcion = new javax.swing.JLabel();

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

        jLabelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/avatardefault.png"))); // NOI18N

        jLabelRol.setForeground(new java.awt.Color(133, 133, 133));
        jLabelRol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelRol.setText("Mi rol");

        jLabelTitle.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        jLabelTitle.setText("My Panel");

        jPanelHeaderView.setOpaque(false);
        jPanelHeaderView.setLayout(new java.awt.BorderLayout());

        jLabelDescripcion.setForeground(new java.awt.Color(102, 102, 102));
        jLabelDescripcion.setText("Descripcion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jLabelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelUserName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelRol))
                            .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))
            .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelProfile;
    private javax.swing.JLabel jLabelRol;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JPanel jPanelHeaderView;
    // End of variables declaration//GEN-END:variables
}

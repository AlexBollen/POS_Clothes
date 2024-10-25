package org.wass.views.component;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.wass.views.listeners.VwActionListener;

/**
 * Un pequeño encabezado para las vistas que implementen un sistema CRU-D.
 * @author wil
 */
public class VwHeader extends JPanel {
    
    /** Evento listar. */
    public static final int BUTTON_LIST = 0xF000;
    /** Evento para un nuevo regitro. */
    public static final int BUTTON_NEW = 0xF001;
    /** Lista de oyentes. */
    private List<VwActionListener> actionListeners = new ArrayList<>();
    
    public VwHeader() {
        initComponents();
    }
    
    /**
     * Agrega un nuevo oyente
     * @param val oyente
     */
    public void addVwActionListener(VwActionListener val) {
        actionListeners.add(val);
    }
    
    /**
     * Dispara todo los oyentes diponibles para ejecutar una acción
     * 
     * @see #BUTTON_LIST
     * @see #BUTTON_NEW
     * 
     * @param tp tipo de acción
     */
    private void fireVwActionListener(int tp) {
        for (final VwActionListener val : actionListeners) {
            val.doAction(tp);
        }
    }
    
    /**
     * Estable un titulo para este header.
     * @param title titulo
     */
    public void setTitle(String title) {
        jLabelTitle.setText(title);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 52, 89));

        jButton1.setBackground(new java.awt.Color(54, 117, 240));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/FileList.png"))); // NOI18N
        jButton1.setText("Listar");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(54, 117, 240));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Save.png"))); // NOI18N
        jButton2.setText("Agregar");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("Vw - Gestion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fireVwActionListener(BUTTON_LIST);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fireVwActionListener(BUTTON_NEW);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration//GEN-END:variables
}

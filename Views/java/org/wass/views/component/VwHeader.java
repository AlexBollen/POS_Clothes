package org.wass.views.component;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SortOrder;

import org.wass.views.component.renderer.MapComboboxModel;
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
    
    // otros
    private Search search;
    
    public VwHeader() {
        initComponents();
        componentesAdd();
    }
    // Componetes adicionales
    private void componentesAdd() {
        search = new Search();
        jPanelSerach.add(search, BorderLayout.CENTER);
        
    }
    
    // Getters
    public Search getSearch() {
        return search;
    }
    public int getLimitSlected() {
        return (int) jSpinner1.getValue();
    }
    
    // Setters
    public void setMapComboboxModel(MapComboboxModel<String, Integer> model) {
        jComboBoxFilter.setModel(model);
    }
    
    /**
     * Agrega un nuevo oyente
     * @param val oyente
     */
    public void addVwActionListener(VwActionListener val) {
        actionListeners.add(val);
    }

    public void hideButtons() {
        jButton1.setVisible(false);
        jButton2.setVisible(false);
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

        buttonGroupOrder = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();
        jPanelFilterAS = new javax.swing.JPanel();
        jPanelSerach = new javax.swing.JPanel();
        jComboBoxFilter = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonAS = new javax.swing.JRadioButton();
        jRadioButtonDES = new javax.swing.JRadioButton();

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

        jPanelFilterAS.setOpaque(false);

        jPanelSerach.setLayout(new java.awt.BorderLayout());

        jComboBoxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFilterActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Límite:");

        buttonGroupOrder.add(jRadioButtonAS);
        jRadioButtonAS.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonAS.setSelected(true);
        jRadioButtonAS.setText("AS");
        jRadioButtonAS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButtonAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonASActionPerformed(evt);
            }
        });

        buttonGroupOrder.add(jRadioButtonDES);
        jRadioButtonDES.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonDES.setText("DES");
        jRadioButtonDES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButtonDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDESActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFilterASLayout = new javax.swing.GroupLayout(jPanelFilterAS);
        jPanelFilterAS.setLayout(jPanelFilterASLayout);
        jPanelFilterASLayout.setHorizontalGroup(
            jPanelFilterASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFilterASLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonDES)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSerach, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelFilterASLayout.setVerticalGroup(
            jPanelFilterASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilterASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilterASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFilterASLayout.createSequentialGroup()
                        .addGroup(jPanelFilterASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jRadioButtonAS)
                            .addComponent(jRadioButtonDES))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelSerach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFilterAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelFilterAS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fireVwActionListener(BUTTON_LIST);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fireVwActionListener(BUTTON_NEW);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        search.filtrar();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jComboBoxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFilterActionPerformed
        search.filtrar();
    }//GEN-LAST:event_jComboBoxFilterActionPerformed

    private void jRadioButtonASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonASActionPerformed
        search.setOrder(SortOrder.ASCENDING);
        search.filtrar();
    }//GEN-LAST:event_jRadioButtonASActionPerformed

    private void jRadioButtonDESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDESActionPerformed
        search.setOrder(SortOrder.DESCENDING);
        search.filtrar();
    }//GEN-LAST:event_jRadioButtonDESActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupOrder;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelFilterAS;
    private javax.swing.JPanel jPanelSerach;
    private javax.swing.JRadioButton jRadioButtonAS;
    private javax.swing.JRadioButton jRadioButtonDES;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}

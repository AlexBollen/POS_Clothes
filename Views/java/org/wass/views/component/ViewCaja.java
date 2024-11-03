package org.wass.views.component;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.wass.controllers.UserController;
import org.wass.controllers.sale.CajaController;
import org.wass.models.ListDataModel;
import org.wass.models.TableDataModel;
import org.wass.models.person.UsuarioModel;
import org.wass.models.sale.CajaModel;
import org.wass.views.component.renderer.MapComboboxModel;
import org.wass.views.listeners.SearchListener;

/**
 * @author wil
 */
public class ViewCaja extends AbstractView implements Control{
    
    private VwHeader header;
    private MapComboboxModel<String, Integer> mapaFiltro;
    private boolean agregar;
    
    private CajaController cajaController;
    private UserController userController;
    private CajaModel cajaActual;
    
    public ViewCaja() {
        initComponents();
        componentesAdd();
    }
    
    private void componentesAdd() {
        mapaFiltro = new MapComboboxModel<>();
        header = new VwHeader();
        views  = new JPanel();
        
        cajaController = new CajaController();
        userController = new UserController();
        
        jComboBoxUsuario.setModel(userController.getListaUsuario());
        
        jTableView.getTableHeader().setReorderingAllowed(false);  
        jTableView.addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("unchecked")
            public void mouseClicked(MouseEvent e) {                
                TableDataModel<CajaModel> dataModel = (TableDataModel<CajaModel>) jTableView.getModel();
                cajaActual = dataModel.getValueAt(jTableView.getSelectedRow());
            }
        });
        
        header.addVwActionListener((target) -> {
            switch (target) {
                case VwHeader.BUTTON_LIST -> {
                    showViewComponent(VC_LISTAR);
                    agregar = false;
                }
                case VwHeader.BUTTON_NEW -> {
                    agregar = true;
                    showViewComponent(VC_ACTUALIZAR);
                }
                default -> throw new AssertionError();
            }
        });
        
        setView(views);
        setHeader(header);
        
        addViewComponent(VC_LISTAR, jPanelListar);
        addViewComponent(VC_ACTUALIZAR, jPanelActualizar);
        
        addVwActionListener((target) -> {
            switch (getCurrentCard()) {
                case VC_LISTAR -> {
                    TableDataModel model = cajaController.obtenerTablaCajas();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        mapaFiltro.put(model.getColumnName(i), i);
                    }
                    
                    jTableView.setModel(model);
                    header.setMapComboboxModel(mapaFiltro);
                    header.getSearch().setTable(jTableView);
                    header.getSearch().setSearchListener(new SearchListener() {
                        @Override
                        public int[] search() {
                            return new int[] {
                                mapaFiltro.getSelected()
                            };
                        }
                        @Override
                        public int limit() {
                            return header.getLimitSlected();
                        }
                    });
                }
                case VC_ACTUALIZAR -> {
                    if (! agregar) {
                        jTextFieldMontoInicial.setText(String.valueOf(cajaActual.getMontoInicial()));
                        jTextFieldMonto.setText(String.valueOf(cajaActual.getMonto()));

                        jCheckBoxEstadoCaja.setSelected(cajaActual.isEstadoCaja());
                        jCheckBoxEstado.setSelected(cajaActual.isEstado());
                        jTextFieldFechaApetura.setText(DateFormat.getInstance().format(cajaActual.getFechaApertura()));
                    } else {
                        jTextFieldMontoInicial.setText("");
                        jTextFieldMonto.setText("");
                        jCheckBoxEstadoCaja.setSelected(true);
                        jCheckBoxEstado.setSelected(true);
                        jTextFieldFechaApetura.setText("");
                    }
                }
                default -> {}
            }
        });
        
        showViewComponent(VC_LISTAR);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelListar = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButtonRight1 = new javax.swing.JButton();
        jButtonRight2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableView = new javax.swing.JTable();
        jPanelActualizar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMontoInicial = new javax.swing.JFormattedTextField();
        jTextFieldMonto = new javax.swing.JFormattedTextField();
        jCheckBoxEstadoCaja = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFechaApetura = new javax.swing.JFormattedTextField();
        jCheckBoxEstado = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButtonLeft = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxUsuario = new javax.swing.JComboBox<>();

        jPanelListar.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(0, 52, 89));

        jButtonRight1.setBackground(new java.awt.Color(54, 117, 240));
        jButtonRight1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRight1.setText("Actualizar");
        jButtonRight1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonRight1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRight1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRight1ActionPerformed(evt);
            }
        });

        jButtonRight2.setBackground(new java.awt.Color(54, 117, 240));
        jButtonRight2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRight2.setText("Eliminar");
        jButtonRight2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonRight2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRight2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRight2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(434, Short.MAX_VALUE)
                .addComponent(jButtonRight2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRight2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanelListar.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBorder(null);

        jTableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableView.setToolTipText("");
        jScrollPane1.setViewportView(jTableView);

        jPanelListar.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        jLabel1.setText("Datos - Caja");

        jLabel2.setText("Monto Inicial:");

        jLabel3.setText("Monto:");

        jTextFieldMontoInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jTextFieldMonto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jCheckBoxEstadoCaja.setSelected(true);
        jCheckBoxEstadoCaja.setText("Estado Caja");

        jLabel4.setText("Fecha Apertura:");

        jTextFieldFechaApetura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jCheckBoxEstado.setSelected(true);
        jCheckBoxEstado.setText("Estado");

        jPanel1.setBackground(new java.awt.Color(0, 52, 89));

        jButtonLeft.setBackground(new java.awt.Color(54, 117, 240));
        jButtonLeft.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLeft.setText("Guardar");
        jButtonLeft.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButtonLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel5.setText("Usuario");

        javax.swing.GroupLayout jPanelActualizarLayout = new javax.swing.GroupLayout(jPanelActualizar);
        jPanelActualizar.setLayout(jPanelActualizarLayout);
        jPanelActualizarLayout.setHorizontalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(22, 22, 22))
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxEstadoCaja))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxEstado)
                                    .addComponent(jTextFieldFechaApetura, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                        .addComponent(jTextFieldMontoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel1))
                        .addContainerGap(255, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelActualizarLayout.setVerticalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMontoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEstadoCaja)
                    .addComponent(jCheckBoxEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldFechaApetura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRight1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRight1ActionPerformed
        if (cajaActual == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un caja primero...");
        } else {
            agregar = false;
            showViewComponent(VC_ACTUALIZAR);
        }
    }//GEN-LAST:event_jButtonRight1ActionPerformed

    @SuppressWarnings("unchecked")
    private void jButtonLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeftActionPerformed
        try {
            
            ListDataModel<UsuarioModel> modeloUsuario = (ListDataModel<UsuarioModel>) jComboBoxUsuario.getModel();
            
            CajaModel caja = new CajaModel();
            caja.setEstado(jCheckBoxEstado.isSelected());
            caja.setEstadoCaja(jCheckBoxEstadoCaja.isSelected());
            caja.setFechaApertura((Date) jTextFieldFechaApetura.getValue());
            caja.setIdUsuario(modeloUsuario.getSelectedModel().getIdUsuario());
            caja.setMonto(Float.parseFloat(jTextFieldMonto.getText()));
            caja.setMontoInicial(Float.parseFloat(jTextFieldMontoInicial.getText()));
            
            if (agregar) {
                cajaController.agregarCaja(caja);
            } else {
                caja.setId(cajaActual.getId());
                cajaController.actualizarCaja(caja);
            }
            showViewComponent(VC_LISTAR);
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonLeftActionPerformed

    private void jButtonRight2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRight2ActionPerformed
        if (cajaActual == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un caja primero...");
        } else {
            cajaController.eliminarCaja(cajaActual);
            cajaActual = null;
        }
    }//GEN-LAST:event_jButtonRight2ActionPerformed

    @Override
    public String getComponenteTitle() {
        return "Control - Cajas";
    }

    private JPanel views;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLeft;
    private javax.swing.JButton jButtonRight1;
    private javax.swing.JButton jButtonRight2;
    private javax.swing.JCheckBox jCheckBoxEstado;
    private javax.swing.JCheckBox jCheckBoxEstadoCaja;
    private javax.swing.JComboBox<Object> jComboBoxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelActualizar;
    private javax.swing.JPanel jPanelListar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableView;
    private javax.swing.JFormattedTextField jTextFieldFechaApetura;
    private javax.swing.JFormattedTextField jTextFieldMonto;
    private javax.swing.JFormattedTextField jTextFieldMontoInicial;
    // End of variables declaration//GEN-END:variables
}

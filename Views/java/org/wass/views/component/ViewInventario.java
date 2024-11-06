/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.wass.views.component;

import org.wass.controllers.ProductoController;
import org.wass.controllers.TipoProductoController;
import org.wass.models.ListDataModel;
import org.wass.models.ProductoModel;
import org.wass.models.TableDataModel;
import org.wass.models.TipoProductoModel;
import org.wass.views.component.renderer.MapComboboxModel;
import org.wass.views.listeners.SearchListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Alex
 */
public class ViewInventario extends AbstractView implements Control {

    private VwHeader header;
    private MapComboboxModel<String, Integer> filterMap;
    private boolean newProduct;

    private ProductoController controller;
    private TipoProductoController tipoProductoController;

    private ProductoModel productSelected;

    public ViewInventario() {
        initComponents();
        componentesAdd();
    }

    private void componentesAdd() {
        filterMap = new MapComboboxModel<>();
        viewPanel = new JPanel();
        header = new VwHeader();
        header.addVwActionListener((target) -> {
            switch (target) {
                case VwHeader.BUTTON_LIST -> {
                    showViewComponent(VC_LISTAR);
                    newProduct = false;
                } case VwHeader.BUTTON_NEW -> {
                    newProduct = true;
                    showViewComponent(VC_ACTUALIZAR);
                }
                default -> throw new AssertionError();
            }
        });

        tipoProductoController = new TipoProductoController();
        controller = new ProductoController();
        controller.setExceptionListener((excptn) -> {
            JOptionPane.showConfirmDialog(this, excptn.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        });

        header.setTitle("Gestión - Inventario");
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("unchecked")
            public void mouseClicked(MouseEvent e) {
                TableDataModel<ProductoModel> model = (TableDataModel<ProductoModel>) jTable1.getModel();
                productSelected = model.getValueAt(jTable1.getSelectedRow());
            }
        });

        setView(viewPanel);
        setHeader(header);

        addViewComponent(VC_LISTAR, jPanelListaProductos);
        addViewComponent(VC_ACTUALIZAR, jPanelActualizar);
        addVwActionListener((target) -> {
            switch (getCurrentCard()) {
                case VC_LISTAR -> {
                    TableModel model = controller.getProductsTable();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        filterMap.put(model.getColumnName(i), i);
                    }

                    jTable1.setModel(model);
                    header.setMapComboboxModel(filterMap);
                    header.getSearch().setTable(jTable1);
                    header.getSearch().setSearchListener(new SearchListener() {
                        @Override
                        public int[] search() {
                            return new int[] {
                              filterMap.getSelected()
                            };
                        }
                        @Override
                        public int limit() {
                            return header.getLimitSlected();
                        }
                    });
                }
                case VC_ACTUALIZAR -> {
                    ListDataModel<TipoProductoModel> modelTipoProducto = tipoProductoController.getTiposProducto();
                    jComboBoxTipoProducto.setModel(modelTipoProducto);

                    if (! newProduct) {
                        jTextFieldNombreProducto.setText(productSelected.getNombreProducto());
                        jTextFieldPrecioCosto.setText(String.valueOf(productSelected.getPrecioCosto()));
                        jTextFieldPrecioVenta.setText(String.valueOf(productSelected.getPrecioVenta()));
                        jTextFieldDescuento.setText(String.valueOf(productSelected.getDescuentoPorcentual()));
                        modelTipoProducto.setDefaultSelectedItem(productSelected.getTipoProducto());
                    } else {
                        jTextFieldNombreProducto.setText("");
                        jTextFieldPrecioCosto.setText("");
                        jTextFieldPrecioVenta.setText("");
                        jTextFieldDescuento.setText("");
                    }
                }
                default -> { }
            }
        });
        showViewComponent(VC_LISTAR);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelListaProductos = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButtonRight1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelActualizar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombreProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPrecioCosto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPrecioVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescuento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTipoProducto = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButtonLeft = new javax.swing.JButton();

        jPanelListaProductos.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(0, 52, 89));

        jButtonRight1.setBackground(new java.awt.Color(54, 117, 240));
        jButtonRight1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRight1.setText("Actualizar");
        jButtonRight1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonRight1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonRight1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRight1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(546, Short.MAX_VALUE)
                .addComponent(jButtonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jButtonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanelListaProductos.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanelListaProductos.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Nombre producto:");

        jLabel2.setText("Precio costo:");

        jLabel3.setText("Precio venta:");

        jTextFieldPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioVentaActionPerformed(evt);
            }
        });

        jLabel4.setText("Descuento porcentual:");

        jLabel5.setText("Tipo de producto:");

        jPanel1.setBackground(new java.awt.Color(0, 52, 89));

        jButtonLeft.setBackground(new java.awt.Color(54, 117, 240));
        jButtonLeft.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLeft.setText("Guardar");
        jButtonLeft.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        javax.swing.GroupLayout jPanelActualizarLayout = new javax.swing.GroupLayout(jPanelActualizar);
        jPanelActualizar.setLayout(jPanelActualizarLayout);
        jPanelActualizarLayout.setHorizontalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97))
                                    .addComponent(jTextFieldPrecioVenta)))
                            .addComponent(jTextFieldNombreProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActualizarLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addGap(309, 309, 309))
                            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBoxTipoProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescuento))
                        .addGap(29, 29, 29))))
        );
        jPanelActualizarLayout.setVerticalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTextFieldDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getComponenteTitle() {
        return "Gestión - Inventario";
    }

    private void jButtonRight1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRight1ActionPerformed
        if (productSelected != null) {
            newProduct = false;
            showViewComponent(VC_ACTUALIZAR);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto...");
        }
    }//GEN-LAST:event_jButtonRight1ActionPerformed

    private void jButtonLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeftActionPerformed
        TipoProductoModel tipoProducto = ((ListDataModel<TipoProductoModel>) jComboBoxTipoProducto.getModel()).getSelectedModel();

        if (newProduct) {
            productSelected = new ProductoModel();
        }
        productSelected.setTipoProducto(tipoProducto);
        productSelected.setNombreProducto(jTextFieldNombreProducto.getText());
        productSelected.setPrecioCosto(Float.valueOf(jTextFieldPrecioCosto.getText()));
        productSelected.setPrecioVenta(Float.valueOf(jTextFieldPrecioVenta.getText()));
        productSelected.setDescuentoPorcentual(Float.valueOf(jTextFieldDescuento.getText()));

        if (newProduct && controller.agregarProducto(productSelected)) {
            JOptionPane.showMessageDialog(null, "Producto registrado");
        } else  if (controller.actualizarProducto(productSelected, productSelected.getId())) {
            JOptionPane.showMessageDialog(null, "Producto actualizado");
        }
        showViewComponent(VC_LISTAR);
    }//GEN-LAST:event_jButtonLeftActionPerformed

    private void jTextFieldPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioVentaActionPerformed

    private JPanel viewPanel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLeft;
    private javax.swing.JButton jButtonRight1;
    private javax.swing.JComboBox<Object> jComboBoxTipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelActualizar;
    private javax.swing.JPanel jPanelListaProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldDescuento;
    private javax.swing.JTextField jTextFieldNombreProducto;
    private javax.swing.JTextField jTextFieldPrecioCosto;
    private javax.swing.JTextField jTextFieldPrecioVenta;
    // End of variables declaration//GEN-END:variables
}

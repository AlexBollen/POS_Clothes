
package org.wass.views.component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.wass.controllers.ClienteController;
import org.wass.models.ClienteModel;

/**
 *
 * @author marco
 */
public class ViewClientes extends javax.swing.JPanel implements Control {

    private ClienteController clienteController;
    private DefaultTableModel clienteTableModel;
    private boolean actualizarB;
    private boolean agregarB;
    private int indiceId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;
    
    /**
     * Creates new form ViewClientes2
     */
    public ViewClientes() {
        this.clienteController = new ClienteController();


        initComponents();
        
        
         //Agregar Modelo para tabla de clientes
        clienteTableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Dirección", "Telefono", "Nit", "Id Persona"}, 0) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        tablaClientes.setModel(clienteTableModel);
        tablaClientes.addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() == 1){
                    accionesExtra.setVisible(true); 
                    txtIdCompra.setText(String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0)));
                    indiceId = Integer.parseInt(String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0))) ;
                    accionesExtra.revalidate();
                    accionesExtra.repaint();
                }
                super.mousePressed(e);
            }
        });
        
        jTituloHeader.setText("Gestión de clientes");
        loadClientes();
    }
    
    public ViewClientes ocultarTodo() {
        btnAgregarCliente.setVisible(false);
        btnListarCliente.setVisible(false);
        accionesExtra.setVisible(false);
        return this;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jTituloHeader = new javax.swing.JLabel();
        btnAgregarCliente = new javax.swing.JButton();
        btnListarCliente = new javax.swing.JButton();
        body = new javax.swing.JPanel();
        listar = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        agregar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelarCompra = new javax.swing.JButton();
        btnGuardarCompra = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        accionesExtra = new javax.swing.JPanel();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtIdCompra = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(500, 595));
        setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(0, 52, 89));
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setMaximumSize(new java.awt.Dimension(100, 43));
        header.setMinimumSize(new java.awt.Dimension(100, 43));
        header.setPreferredSize(new java.awt.Dimension(100, 43));

        jTituloHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        jTituloHeader.setText("Gestión de Clientes");

        btnAgregarCliente.setBackground(new java.awt.Color(0, 168, 232));
        btnAgregarCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarCliente.setForeground(new java.awt.Color(237, 242, 244));
        btnAgregarCliente.setText("AGREGAR");
        btnAgregarCliente.setPreferredSize(new java.awt.Dimension(106, 31));
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        btnListarCliente.setBackground(new java.awt.Color(0, 168, 232));
        btnListarCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnListarCliente.setForeground(new java.awt.Color(237, 242, 244));
        btnListarCliente.setText("LISTAR");
        btnListarCliente.setPreferredSize(new java.awt.Dimension(106, 31));
        btnListarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTituloHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnListarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTituloHeader))
                .addContainerGap())
        );

        add(header, java.awt.BorderLayout.PAGE_START);

        body.setBackground(new java.awt.Color(237, 242, 244));
        body.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        body.setMaximumSize(new java.awt.Dimension(0, 0));
        body.setMinimumSize(new java.awt.Dimension(0, 0));
        body.setLayout(new java.awt.CardLayout());

        listar.setBackground(new java.awt.Color(237, 242, 244));
        listar.setMaximumSize(new java.awt.Dimension(0, 0));
        listar.setPreferredSize(new java.awt.Dimension(0, 0));
        listar.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setBackground(new java.awt.Color(255, 209, 102));
        jScrollPane6.setMaximumSize(new java.awt.Dimension(1000, 1000));

        tablaClientes.setBackground(new java.awt.Color(255, 255, 255));
        tablaClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaClientes.setFillsViewportHeight(true);
        tablaClientes.setShowGrid(true);
        jScrollPane6.setViewportView(tablaClientes);

        listar.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        body.add(listar, "listar");

        agregar.setBackground(new java.awt.Color(255, 255, 255));
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 52, 89));

        btnCancelarCompra.setBackground(new java.awt.Color(0, 168, 232));
        btnCancelarCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelarCompra.setForeground(new java.awt.Color(237, 242, 244));
        btnCancelarCompra.setText("CANCELAR");
        btnCancelarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCompraActionPerformed(evt);
            }
        });

        btnGuardarCompra.setBackground(new java.awt.Color(0, 168, 232));
        btnGuardarCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarCompra.setForeground(new java.awt.Color(237, 242, 244));
        btnGuardarCompra.setText("GUARDAR");
        btnGuardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(518, Short.MAX_VALUE)
                .addComponent(btnCancelarCompra)
                .addGap(30, 30, 30)
                .addComponent(btnGuardarCompra)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnGuardarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        agregar.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(237, 242, 244));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nombre");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Dirección");

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Telefono");

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Nit");

        txtNit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(57, 57, 57)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(49, 49, 49)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(86, 86, 86))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(437, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNit)
                    .addComponent(jLabel12))
                .addGap(243, 243, 243))
        );

        agregar.add(jPanel3, java.awt.BorderLayout.CENTER);

        body.add(agregar, "agregar");

        add(body, java.awt.BorderLayout.CENTER);

        accionesExtra.setBackground(new java.awt.Color(0, 52, 89));
        accionesExtra.setEnabled(false);

        jButtonActualizar.setBackground(new java.awt.Color(0, 168, 232));
        jButtonActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonActualizar.setForeground(new java.awt.Color(237, 242, 244));
        jButtonActualizar.setText("ACTUALIZAR");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setBackground(new java.awt.Color(0, 168, 232));
        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEliminar.setForeground(new java.awt.Color(237, 242, 244));
        jButtonEliminar.setText("ELIMINAR");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Id Cliente:");

        javax.swing.GroupLayout accionesExtraLayout = new javax.swing.GroupLayout(accionesExtra);
        accionesExtra.setLayout(accionesExtraLayout);
        accionesExtraLayout.setHorizontalGroup(
            accionesExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accionesExtraLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        accionesExtraLayout.setVerticalGroup(
            accionesExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accionesExtraLayout.createSequentialGroup()
                .addGroup(accionesExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonActualizar)
                    .addComponent(jButtonEliminar)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        add(accionesExtra, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

   
    @Override
    public String getComponenteTitle() {
        return "Clientes";
    }

    
    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        jTituloHeader.setText("Agregar nuevo cliente");
        CardLayout l = (CardLayout) body.getLayout();
        l.show(body, "agregar");
        limpiarDatos();
        agregarB = true;
        actualizarB = false;
        
        
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnListarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClienteActionPerformed
        jTituloHeader.setText("Gestión de clientes");
       CardLayout l = (CardLayout) body.getLayout();
        l.show(body, "listar");
        loadClientes();
    }//GEN-LAST:event_btnListarClienteActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        // TODO add your handling code here:
        llenarTextBox();
        jTituloHeader.setText("Actualizar cliente con id: "+ indiceId);
        limpiarDatos();
        CardLayout l = (CardLayout) body.getLayout();
        l.show(body, "agregar");
        agregarB = false;
        actualizarB = true;
        
        
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtNit.setText(nit);
        
       
        
        
        
      
     
        
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
         int opcion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de eliminar al cliente: "+String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1))+"?",
             "Eliminar cliente",
            JOptionPane.OK_CANCEL_OPTION);
       if (opcion == JOptionPane.OK_OPTION){
           eliminarCliente();
       }
      
        
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void btnCancelarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCompraActionPerformed
        // TODO add your handling code here:
        CardLayout l = (CardLayout) body.getLayout();
        l.show(body, "listar");
        loadClientes();
    }//GEN-LAST:event_btnCancelarCompraActionPerformed

    private void btnGuardarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCompraActionPerformed
        if(agregarB){
            guardarCliente();
        }
        
        else if(actualizarB){
            actualizarCliente();
        }
        
        actualizarB = false;
        agregarB = false;
        
    }//GEN-LAST:event_btnGuardarCompraActionPerformed
 
    
    
    private void limpiarDatos(){
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtNit.setText("");
        clienteTableModel.setRowCount(0);
        accionesExtra.setVisible(false);
    }
    
    private void llenarTextBox(){
        nombre = String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1));
        direccion = String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2));
        telefono = String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3));
        nit = String.valueOf(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 4));
    }
    
     private void eliminarCliente() {
         
        int selectedRow = tablaClientes.getSelectedRow();
        if (selectedRow >= 0) {
            clienteTableModel.removeRow(selectedRow);
           boolean   resultado = clienteController.eliminarCliente(indiceId);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Cliente exterminado exitosamente.");
                limpiarDatos();
                loadClientes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.");
            }        
           
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.");
        }
    }
     
     private void guardarCliente(){
         ClienteModel cliente = null;
         
         try{
             cliente = new ClienteModel(0, "","","","");
             cliente.setNombrePersona(txtNombre.getText());
             cliente.setDireccion(txtDireccion.getText());
             cliente.setTelefono(txtTelefono.getText());
             cliente.setNit(txtNit.getText());
             
             boolean resultado = clienteController.agregarClientePersona(cliente);
             
              if (resultado) {
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
                limpiarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el cliente.");
            }        
             
         }
         catch (Exception e){
             JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
         }
         
         
         jTituloHeader.setText("Gestión de clietnes");
         CardLayout l = (CardLayout) body.getLayout();
            l.show(body, "listar");
         loadClientes();
     }
     
     private void actualizarCliente(){
         ClienteModel cliente = null;
         try{
             cliente = new ClienteModel(0, "","","","");
             cliente.setNombrePersona(txtNombre.getText());
             cliente.setDireccion(txtDireccion.getText());
             cliente.setTelefono(txtTelefono.getText());
             cliente.setNit(txtNit.getText());
             
             boolean resultado = clienteController.actualizarClientePersona(cliente, indiceId);
             
              if (resultado) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
                limpiarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el cliente.");
            }        
             
         }
         catch (Exception e){
             JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
         }
         
         jTituloHeader.setText("Gestión de clientes ");
         CardLayout l = (CardLayout) body.getLayout();
         l.show(body, "listar");
         loadClientes();
     }
    
 private void loadClientes(){
        limpiarDatos();
        List<ClienteModel> listaClientes = clienteController.obtenerClientes();

        for (ClienteModel cliente : listaClientes) {
        
        // Insertar los datos en la tabla
        clienteTableModel.addRow(new Object[]{
            cliente.getIdCliente(),
            cliente.getNombrePersona(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            cliente.getNit(),
            cliente.getIdPersona()
          
        });
        
        
    // Asignar el modelo a la tabla
    
    tablaClientes.setModel(clienteTableModel);
    }
    
 }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accionesExtra;
    private javax.swing.JPanel agregar;
    private javax.swing.JPanel body;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnCancelarCompra;
    private javax.swing.JButton btnGuardarCompra;
    private javax.swing.JButton btnListarCliente;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel jTituloHeader;
    private javax.swing.JPanel listar;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

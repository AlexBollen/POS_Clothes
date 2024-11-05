package org.wass.views.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import org.wass.controllers.UserController;
import org.wass.controllers.PersonaController;
import org.wass.controllers.RolController;
import org.wass.models.ListDataModel;
import org.wass.models.TableDataModel;
import org.wass.models.PersonaModel;
import org.wass.models.RolModel;
import org.wass.models.UsuarioModel;
import org.wass.views.component.renderer.MapComboboxModel;
import org.wass.views.listeners.SearchListener;

/**
 * @author wil
 */
public class ViewUsuario extends AbstractView implements Control {
    
    private VwHeader header;
    private MapComboboxModel<String, Integer> mapFiltro;
    private boolean nuevo;
    
    private UserController controller;
    private PersonaController personaController;
    private RolController rolController;
    
    private UsuarioModel userSelected;
    
    public ViewUsuario() {
        initComponents();
        componentesAdd();
    }
    
    private void componentesAdd() {
        mapFiltro = new MapComboboxModel<>();
        viewPanel = new JPanel();
        header = new VwHeader();
        header.addVwActionListener((target) -> {
            switch (target) {
                case VwHeader.BUTTON_LIST -> {
                    showViewComponent(VC_LISTAR);
                    nuevo = false;
                }
                case VwHeader.BUTTON_NEW -> {
                    nuevo = true;
                    showViewComponent(VC_ACTUALIZAR);
                }
                default -> throw new AssertionError();
            }
        });
        
        personaController = new PersonaController();
        rolController = new RolController();
        controller = new UserController();
        controller.setExceptionListener((excptn) -> {
            JOptionPane.showConfirmDialog(this, excptn.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        });
        
        header.setTitle("Gestión - Usuarios");
        jTable1.getTableHeader().setReorderingAllowed(false);        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("unchecked")
            public void mouseClicked(MouseEvent e) {                
                TableDataModel<UsuarioModel> model =  (TableDataModel<UsuarioModel>) jTable1.getModel();
                userSelected = model.getValueAt(jTable1.getSelectedRow());
            }
        });
        
        setView(viewPanel);
        setHeader(header);
        
        addViewComponent(VC_LISTAR, jPanelListaUsuario);
        addViewComponent(VC_ACTUALIZAR, jPanelActualizar);
        addVwActionListener((target) -> {
            switch (getCurrentCard()) {
                case VC_LISTAR -> {
                    TableModel model = controller.getTablaUsuario();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        mapFiltro.put(model.getColumnName(i), i);
                    }
                    
                    jTable1.setModel(model);
                    header.setMapComboboxModel(mapFiltro);
                    header.getSearch().setTable(jTable1);
                    header.getSearch().setSearchListener(new SearchListener() {
                        @Override
                        public int[] search() {
                            return new int[] {
                                mapFiltro.getSelected()
                            };
                        }
                        @Override
                        public int limit() {
                            return header.getLimitSlected();
                        }
                    });
                }
                case VC_ACTUALIZAR -> {
                    jCheckBoxNewContra.setVisible(!nuevo);
                    jPasswordPass.setEditable(nuevo);
                    
                    ListDataModel<PersonaModel> modelPersona = personaController.obtenerTodoPersonas();                    
                    jComboBoxPersona.setModel(modelPersona);
                    
                    ListDataModel<RolModel> modelRol = rolController.obtenerTodoRoles();
                    jComboBoxRol.setModel(modelRol);
                    
                    if (! nuevo) {
                        jTextFieldNombreUsuario.setText(userSelected.getNombreUsuario());
                        jPasswordPass.setText("");
                        jCheckBoxActivo.setSelected(userSelected.getEstado());
                        
                        modelPersona.setDefaultSelectedItem(userSelected.getIdPersona());
                        modelRol.setDefaultSelectedItem(userSelected.getRol());
                    } else {
                        jTextFieldNombreUsuario.setText("");
                        jPasswordPass.setText("");
                        jCheckBoxActivo.setSelected(true);
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

        jPanelListaUsuario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButtonRight1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelActualizar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombreUsuario = new javax.swing.JTextField();
        jCheckBoxNewContra = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jPasswordPass = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxRol = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxPersona = new javax.swing.JComboBox<>();
        jCheckBoxActivo = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonLeft = new javax.swing.JButton();

        jPanelListaUsuario.setLayout(new java.awt.BorderLayout());

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

        jPanelListaUsuario.add(jPanel4, java.awt.BorderLayout.PAGE_END);

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

        jPanelListaUsuario.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Usuario:");

        jCheckBoxNewContra.setText("Nuevo [ Clave de Acceso ]");
        jCheckBoxNewContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxNewContraActionPerformed(evt);
            }
        });

        jLabel2.setText("Clave de Acceso:");

        jPasswordPass.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel3.setText("Dueño y Nivel en Sistema");

        jLabel4.setText("Rol");

        jLabel5.setText("Persona");

        jCheckBoxActivo.setText("Usuario Activo");

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

        javax.swing.GroupLayout jPanelActualizarLayout = new javax.swing.GroupLayout(jPanelActualizar);
        jPanelActualizar.setLayout(jPanelActualizarLayout);
        jPanelActualizarLayout.setHorizontalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNombreUsuario)
                                    .addComponent(jPasswordPass, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBoxNewContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBoxActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(27, 27, 27))
                    .addGroup(jPanelActualizarLayout.createSequentialGroup()
                        .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxRol, 0, 161, Short.MAX_VALUE))
                                .addGroup(jPanelActualizarLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxPersona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelActualizarLayout.setVerticalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActualizarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordPass)
                        .addComponent(jCheckBoxNewContra))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getComponenteTitle() {
        return "Gestión - Usuario";
    }

    private void jCheckBoxNewContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxNewContraActionPerformed
        jPasswordPass.setEditable(jCheckBoxNewContra.isSelected());
    }//GEN-LAST:event_jCheckBoxNewContraActionPerformed

    private void jButtonRight1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRight1ActionPerformed
        if (userSelected != null) {
            nuevo = false;
            showViewComponent(VC_ACTUALIZAR);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario primero...");
        }
    }//GEN-LAST:event_jButtonRight1ActionPerformed

    @SuppressWarnings("unchecked")
    private void jButtonLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeftActionPerformed
        PersonaModel persona = ((ListDataModel<PersonaModel>) jComboBoxPersona.getModel()).getSelectedModel();
        RolModel rol = ((ListDataModel<RolModel>) jComboBoxRol.getModel()).getSelectedModel();
        
        if (nuevo) {
            userSelected = new UsuarioModel();
        }
        
        userSelected.setPersona(persona);
        userSelected.setRol(rol);
        
        if (jCheckBoxNewContra.isSelected() && !nuevo) {
            userSelected.setContrasenia(new String(jPasswordPass.getPassword()));
        } else {
            userSelected.setContrasenia(new String(jPasswordPass.getPassword()));
        }
        userSelected.setNombreUsuario(jTextFieldNombreUsuario.getText());
        userSelected.setEstado(jCheckBoxActivo.isSelected());
        
        if (nuevo && controller.nuevo(userSelected)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado");
        } else  if (controller.actualizar(userSelected, jCheckBoxNewContra.isSelected())) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado");
        }
        showViewComponent(VC_LISTAR);
    }//GEN-LAST:event_jButtonLeftActionPerformed

    private JPanel viewPanel;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLeft;
    private javax.swing.JButton jButtonRight1;
    private javax.swing.JCheckBox jCheckBoxActivo;
    private javax.swing.JCheckBox jCheckBoxNewContra;
    private javax.swing.JComboBox<Object> jComboBoxPersona;
    private javax.swing.JComboBox<Object> jComboBoxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelActualizar;
    private javax.swing.JPanel jPanelListaUsuario;
    private javax.swing.JPasswordField jPasswordPass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    // End of variables declaration//GEN-END:variables
}

package org.wass.views.main;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JViewport;
import org.wass.models.Usuario;
import org.wass.views.component.Dashboard;

/**
 *
 * @author marco
 * @author wil
 */
public class MainFrame extends JFrame {

    private String credencialesUsuario = "usuario";
    private Usuario logedUser;
    
    private Dashboard dashboard;
    
    public MainFrame(Usuario logedUser) {
        this.logedUser = logedUser;
        this.credencialesUsuario = logedUser.getNombrePersona() + " : " + logedUser.getRol().getNombreRol();
        initComponents();
        componentesAdd();
    }
    
    private void componentesAdd() {
        dashboard = new Dashboard();
        
        jButtonSettings.setFocusPainted(false);
        changeView(dashboard);
        
        header2.setLabelText("Dashboard - " + credencialesUsuario);
        menu1.setEvent((int index, int subIndex) -> {
            //Aquí se llaman a los formularios
            switch (index) {
                case 0 -> {
                    //Dashboard
                    changeView(dashboard);
                    header2.setLabelText("Dashboard - " + credencialesUsuario);
                }

                case 1 -> {
                    //Ventas
                    if (subIndex == 1) { //Nueva venta
                        //showForm(new Formulario());
                        header2.setLabelText("Nueva venta - " + credencialesUsuario);
                    } else if (subIndex == 2) { //Historial
                        header2.setLabelText("Historial - " + credencialesUsuario);
                    }
                }
                case 2 -> //Inventario
                    header2.setLabelText("Inventario - " + credencialesUsuario);
                case 3 -> //Compras
                    header2.setLabelText("Compras - " + credencialesUsuario);
                case 4 -> {
                    //Beneficiarios
                    if (subIndex == 1) {//Clientes
                        header2.setLabelText("Clientes - " + credencialesUsuario);
                    } else if (subIndex == 2) {//Proveedores
                        header2.setLabelText("Proveedores - " + credencialesUsuario);
                    }
                }
                case 5 -> //Cajas
                    header2.setLabelText("Cajas - " + credencialesUsuario);
                case 6 -> //Reportes
                    header2.setLabelText("Reportes - " + credencialesUsuario);
                default -> {
                }
            }
        });

        // Centrar form en la pantalla
        setLocationRelativeTo(null);
    }
    
    private void changeView(Component component) {
        JViewport viewport = rootView.getViewport();
        if (viewport.getView() != component) {
            rootView.setViewportView(component);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRoot = new javax.swing.JPanel();
        jPanelView = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jPanelMenuHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        menu1 = new org.wass.views.menu.Menu();
        jPanel8 = new javax.swing.JPanel();
        jButtonSettings = new javax.swing.JButton();
        jPanelBody = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelHeaderView = new javax.swing.JPanel();
        header2 = new org.wass.views.component.Header();
        rootView = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WASS");
        setSize(new java.awt.Dimension(900, 500));

        jPanelView.setBackground(new java.awt.Color(255, 255, 255));
        jPanelView.setLayout(new java.awt.BorderLayout());

        jPanelMenu.setBackground(new java.awt.Color(247, 248, 250));
        jPanelMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(231, 231, 231)));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(250, 438));
        jPanelMenu.setLayout(new java.awt.BorderLayout());

        jPanelMenuHeader.setBackground(new java.awt.Color(247, 248, 250));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/favicon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("WASS - POS Clothes");

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("v.1.0.0");

        javax.swing.GroupLayout jPanelMenuHeaderLayout = new javax.swing.GroupLayout(jPanelMenuHeader);
        jPanelMenuHeader.setLayout(jPanelMenuHeaderLayout);
        jPanelMenuHeaderLayout.setHorizontalGroup(
            jPanelMenuHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenuHeaderLayout.setVerticalGroup(
            jPanelMenuHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelMenuHeaderLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanelMenu.add(jPanelMenuHeader, java.awt.BorderLayout.PAGE_START);

        menu1.setMinimumSize(new java.awt.Dimension(266, 354));
        menu1.setPreferredSize(new java.awt.Dimension(266, 354));
        jPanelMenu.add(menu1, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(247, 248, 250));

        jButtonSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/setting.png"))); // NOI18N
        jButtonSettings.setBorder(null);
        jButtonSettings.setContentAreaFilled(false);
        jButtonSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSettings.setFocusPainted(false);
        jButtonSettings.setFocusable(false);
        jButtonSettings.setRequestFocusEnabled(false);
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(jButtonSettings)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jButtonSettings)
                .addContainerGap())
        );

        jPanelMenu.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanelView.add(jPanelMenu, java.awt.BorderLayout.LINE_START);

        jPanelBody.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBody.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanelHeaderView.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHeaderView.setLayout(new java.awt.BorderLayout());
        jPanelHeaderView.add(header2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBody.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        rootView.setBackground(new java.awt.Color(255, 255, 255));
        rootView.setBorder(null);
        jPanelBody.add(rootView, java.awt.BorderLayout.CENTER);

        jPanelView.add(jPanelBody, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanelRootLayout = new javax.swing.GroupLayout(jPanelRoot);
        jPanelRoot.setLayout(jPanelRootLayout);
        jPanelRootLayout.setHorizontalGroup(
            jPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelRootLayout.setVerticalGroup(
            jPanelRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelView, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelRoot, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSettingsActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.wass.views.component.Header header2;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHeaderView;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMenuHeader;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelView;
    private javax.swing.JSeparator jSeparator1;
    private org.wass.views.menu.Menu menu1;
    private javax.swing.JScrollPane rootView;
    // End of variables declaration//GEN-END:variables
}

package org.wass.views;

import java.awt.Component;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JViewport;

import org.wass.controllers.LoginController;
import org.wass.controllers.product.ProductoController;
import org.wass.controllers.product.StockController;
import org.wass.controllers.purchase.CompraController;
import org.wass.controllers.purchase.DetalleCompraController;
import org.wass.controllers.purchase.EstadoCompraController;
import org.wass.controllers.purchase.ProveedorController;
import org.wass.controllers.purchase.TipoPagoController;

import org.wass.controllers.sale.CajaController;
import org.wass.controllers.sale.ClienteController;
import org.wass.controllers.purchase.ProveedorController;

import org.wass.controllers.sale.FacturaController;
import org.wass.controllers.sale.SerieFacturaController;
import org.wass.models.person.UsuarioModel;
import org.wass.models.product.ProductoDAO;
import org.wass.models.product.StockDAO;
import org.wass.models.purchase.CompraDAO;
import org.wass.models.purchase.DetalleCompraDAO;
import org.wass.models.purchase.EstadoCompraDAO;
import org.wass.models.purchase.ProveedorDAO;
import org.wass.models.purchase.TipoPagoDAO;
import org.wass.models.sale.*;

import org.wass.views.component.Control;
import org.wass.views.component.Dashboard;
import org.wass.views.component.ViewCaja;
import org.wass.views.component.ViewUsuario;
import org.wass.views.component.menu.MenuConfiguraciones;
import org.wass.views.sale.ViewClientes2;
import org.wass.views.purchase.ViewProveedores;

/**
 *
 * @author marco
 * @author wil
 */
public class MainFrame extends AbstractFrame {

    private UsuarioModel logedUser;
    private Dashboard dashboard;

    private FloatingWindow configuraciones;
    
    public MainFrame(UsuarioModel logedUser) {
        this.logedUser = logedUser;
        initComponents();
        componentesAdd();
    }

    private void componentesAdd() {
        dashboard = new Dashboard();
        configuraciones = new FloatingWindow(this, false);

        // <editor-fold defaultstate="collapsed" desc="Instanciar Controladores"> 
        
        //Instancia para ProductoDAO y ProductoController
        ProductoDAO productodao = new ProductoDAO();
        ProductoController productocontroller = new ProductoController(productodao);
        
        //Instancia para ProveedorDAO y ProveedorController
        ProveedorDAO proveedordao = new ProveedorDAO();
        ProveedorController proveedorcontroller = new ProveedorController(proveedordao);
        
        //Instancia para TipoPagoDAO y TipoPagoController
        TipoPagoDAO tipopagodao = new TipoPagoDAO();
        TipoPagoController tipopagocontroller = new TipoPagoController(tipopagodao);
        
        //Instancia para EstadoCompraDAO y EstadoCompraController
        EstadoCompraDAO estadocompradao = new EstadoCompraDAO();
        EstadoCompraController estadocompracontroller = new EstadoCompraController(estadocompradao);
        
        //Instancia para DetalleCompraDAO y DetalleCompraController
        DetalleCompraDAO detallecompradao = new DetalleCompraDAO();
        DetalleCompraController detallecompracontroller = new DetalleCompraController(detallecompradao);
        
        //Instancia para CompraDAO y CompraController
        CompraDAO compradao = new CompraDAO();
        CompraController compracontroller = new CompraController(compradao,detallecompradao);
        
        //Instancia para FacturaDAO y FacturaController
        FacturaDao facturaDao = new FacturaDao();
        FacturaController facturaController = new FacturaController(facturaDao);

        //Instancia para SerieFacturaDAO y SerieFacturaController
        SerieFacturaDao serieFacturaDao = new SerieFacturaDao();
        SerieFacturaController serieFacturaController = new SerieFacturaController(serieFacturaDao);

        //Instancia para StockDAO y StockController
        StockDAO stockDao = new StockDAO();
        StockController stockController = new StockController(stockDao);

        //Instancia para CajaDAO y CajaController
        CajaDAO cajaDAO = new CajaDAO();
        CajaController cajaController = new CajaController(cajaDAO);

        //Instancia para ClienteDAO y ClienteController
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteController clienteController = new ClienteController(clienteDAO);
        
        //Instancia para ClienteDAO y ClienteController
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        ProveedorController proveedorController = new ProveedorController(proveedorDAO);

        // </editor-fold>

        jButtonSettings.setFocusPainted(false);
        changeView(dashboard);

        header2.setUserName(logedUser.getNombrePersona());
        header2.setUserRol(logedUser.getRol().getNombreRol());
        header2.setProfile(new ImageIcon(getClass().getResource("/Iconos/avatardefault.png")).getImage());

        //Aquí se llaman a los formularios
        menu1.setEvent((int index, int subIndex) -> {
            switch (index) {
                case 0 -> {
                    //Dashboard
                    changeView(dashboard);
                }
                case 1 -> {
                    switch (subIndex) {
                        case 1 -> {
                            //Validación para verificar que el usuario logeado tenga una caja activa
                            CajaModel cajaUsuario = cajaDAO.obtenerCajaUsuario(logedUser.getIdUsuario());
                            if (cajaUsuario != null) {
                                //Nueva venta
                                changeView(new POS(facturaController, serieFacturaController, stockController, clienteController, cajaUsuario));
                            } else {
                                JOptionPane.showMessageDialog(this, "El usuario no tiene una caja activa");
                            }

                        }
                        case 2 -> {
                            //Historial
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                case 2 -> {
                    //Inventario
                }
                case 3 -> {
                    changeView(new VistaCompras(productocontroller,proveedorcontroller,tipopagocontroller,estadocompracontroller,compracontroller));
                    //Compras
                }
                case 4 -> {
                    switch (subIndex) {
                        case 1 -> {
                            //Clientes
                            changeView(new ViewClientes2(clienteController));
                        }
                        case 2 -> {
                            //Proveedores
                             changeView(new ViewProveedores(proveedorController));
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                case 5 -> {
                    //Cajas
                    changeView(new ViewCaja());
                }
                case 6 -> {
                    //Reportes
                }
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

            if (component instanceof Control control) {
                header2.setHeaderTitle(control.getComponenteTitle());
            }
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
        menu1 = new org.wass.views.component.menu.Menu();
        jPanel8 = new javax.swing.JPanel();
        jButtonSettings = new javax.swing.JButton();
        jPanelBody = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanelHeaderView = new javax.swing.JPanel();
        header2 = new org.wass.views.component.Header();
        rootView = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WASS");
        setSize(new java.awt.Dimension(900, 500));

        jPanelView.setBackground(new java.awt.Color(255, 255, 255));
        jPanelView.setLayout(new java.awt.BorderLayout());

        jPanelMenu.setBackground(new java.awt.Color(43, 45, 48));
        jPanelMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(250, 438));
        jPanelMenu.setLayout(new java.awt.BorderLayout());

        jPanelMenuHeader.setBackground(new java.awt.Color(30, 31, 34));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/favicon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(188, 188, 188));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("WASS - POS Clothes");

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(179, 179, 179));
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

        jPanel8.setBackground(new java.awt.Color(30, 31, 34));

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

        jPanel7.setBackground(new java.awt.Color(244, 244, 244));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));

        jPanelHeaderView.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHeaderView.setLayout(new java.awt.BorderLayout());

        header2.setClonseListener((target) -> {
            setVisible(false);
            dispose();

            new FormLogin(new LoginController()).setVisible(true);
        });
        jPanelHeaderView.add(header2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelHeaderView, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(16, 16, 16))
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
        if (configuraciones.isVisible()) {
            return;
        }
        configuraciones.setTitle("Configuraciones");
        configuraciones.setView(new MenuConfiguraciones().addOyenteConfiguracion((op) -> {
            switch (op) {
                case Usuario -> { 
                    ViewUsuario vwUsuario = new ViewUsuario();
                    changeView(vwUsuario);

                    configuraciones.doClose(FloatingWindow.RET_OK);
                }
                case Aplicacion -> {
                    /* nada */
                }
                default -> throw new AssertionError();
            }
        })).setVisible(true);

        Point point = jButtonSettings.getLocationOnScreen();
        Dimension size = jButtonSettings.getPreferredSize();        
        configuraciones.setLocation(point.x + (size.height / 2), 
                                    point.y - (configuraciones.getPreferredSize().height + (size.height/2)));
    }//GEN-LAST:event_jButtonSettingsActionPerformed

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
    private org.wass.views.component.menu.Menu menu1;
    private javax.swing.JScrollPane rootView;
    // End of variables declaration//GEN-END:variables
}

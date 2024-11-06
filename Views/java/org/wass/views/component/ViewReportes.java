package org.wass.views.component;

import javax.swing.JPanel;

/**
 * @author wil
 */
public class ViewReportes extends JPanel implements Control {
    
    public ViewReportes() {
        initComponents();
        componetesAdd();
    }
    
    private void componetesAdd() {
        reportes.addTab("Caja", new ViewCaja().ocultarTodo());
        reportes.addTab("Clientes", new ViewClientes().ocultarTodo());
        reportes.addTab("Compras", new ViewCompras().ocultarTodo());
        reportes.addTab("Proveedores", new ViewProveedores().ocultarTodo());
        reportes.addTab("Usuarios", new ViewUsuario().ocultarTodo());
        reportes.addTab("Ventas", new ViewVentas());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportes = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());
        add(reportes, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getComponenteTitle() {
        return "Reportes";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane reportes;
    // End of variables declaration//GEN-END:variables
}

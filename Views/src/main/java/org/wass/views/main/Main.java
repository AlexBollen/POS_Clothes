package org.wass.views.main;
import java.awt.Component;
import org.wass.views.menu.MenuEvent;
import org.wass.views.component.DashboardForm;
import org.wass.views.component.Header;
/**Aquí se importan los formularios
 * Eje: import org.wass.views.paquete.formulario;
*/

/**
 *
 * @author marco
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    private String usuario = "usuario";
    
    public Main() {
        initComponents();
        header1.setLabelText("Dashboard - "+usuario);
        showForm(new DashboardForm());
            menu1.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                System.out.println(index + "" + subIndex );
                //Aquí se llaman a los formularios
                if (index == 0){ //Dashboard
                    showForm(new DashboardForm());
                    header1.setLabelText("Dashboard - "+usuario);
                }
                else if(index==1){ //Ventas
                    if(subIndex==1){ //Nueva venta
                        //showForm(new Formulario());
                        header1.setLabelText("Nueva venta - "+usuario);
                    }
                    else if(subIndex==2){ //Historial
                        header1.setLabelText("Historial - "+usuario);
                    }
                }
                else if(index==2){//Inventario
                    header1.setLabelText("Inventario - "+usuario);
                }
                else if(index==3){//Compras
                    header1.setLabelText("Compras - "+usuario);
                }
                else if(index==4){//Beneficiarios
                    if(subIndex==1){//Clientes
                        header1.setLabelText("Clientes - "+usuario);
                    }
                    else if(subIndex==2){//Proveedores
                        header1.setLabelText("Proveedores - "+usuario);
                    }
                }
                else if(index==5){//Cajas
                    header1.setLabelText("Cajas - "+usuario);
                }
                else if(index==6){//Reportes
                    header1.setLabelText("Reportes - "+usuario);
                }
                /*if (index == 0) {
                    showForm(new HomeForm());
                } else {
                    showForm(new DefaultForm("Form : " + index + " " + subIndex));
                }*/
            }
        });
    }
    
      private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPaneWin111 = new org.wass.views.scroll.win11.ScrollPaneWin11();
        menu1 = new org.wass.views.menu.Menu();
        header1 = new org.wass.views.component.Header();
        body = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wass");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(221, 221, 218));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)));
        jPanel1.setForeground(new java.awt.Color(94, 142, 153));

        scrollPaneWin111.setBackground(new java.awt.Color(94, 142, 153));

        menu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu1MouseClicked(evt);
            }
        });
        scrollPaneWin111.setViewportView(menu1);

        body.setForeground(new java.awt.Color(221, 221, 218));
        body.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(94, 142, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menu1MouseClicked

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private org.wass.views.component.Header header1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.wass.views.menu.Menu menu1;
    private org.wass.views.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}

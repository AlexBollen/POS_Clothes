/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.wass.controllers.Product.ProductoController;
import org.wass.controllers.Purchase.*;
import org.wass.models.purchase.*;
import org.wass.views.utilities.CustomFont;
import org.wass.controllers.db.DBConfig;
import org.wass.controllers.LoginController;
import org.wass.models.UsuarioDao;
import static org.wass.controllers.db.DBConfig.*;

import org.wass.models.product.ProductoDAO;

import org.wass.controllers.Product.TipoProductoController;
import org.wass.models.product.TipoProductoDAO;
import packages.CompraView;
import packages.ProductoView;
import packages.TipoProductoView;

/**
 * Clase principal encargada de gestionar la entrada y salida de aplicación 
 * <b>POS Clothes</b>, este es el punto de montaje del software en donde solo se
 * llaman los objetos principales de partida.
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class App {
    /** Logger de la clase. */
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    /**
     * El método principal; utiliza cero argumentos en el arreglo args.
     * @param args argumentos de la línea de comando
     */
    public static void main(String[] args) {     
        // Cargar y registrar fuentes
        CustomFont.cargarFuentes("/fonts/roboto/", "roboto-fonts.properties");
        CustomFont.cargarFuentes("/fonts/Cantarell/", "Cantarell.properties");
        
        DBConfig config = nDataBaseConfig();
        config.set(DataConfig.MySQLDataBase, "clothesbd");
        config.set(DataConfig.MySQLUserName, "");
        config.set(DataConfig.MySQLUserPassword, "");

        /*
         * Establecer un 'LookAndFeel' del tipo 'flat' si es posibles, de lo
         * contrario se utilizara el tema predeterminado por el sistema Swinf/AWT.
         */
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.log(Level.WARNING, "Error al establecer el tema: {0}", e.getMessage());
        }
        
        // Creación de instancia UsuarioDao para ser utilizado
        // en la validación de credenciales
        UsuarioDao userDao = new UsuarioDao();
        
        // Creación de instancia del controlador de login con UsuarioDao
        // para que interactúe con la bd a través de este
        LoginController loginController = new LoginController(userDao);

        ProductoDAO productDao = new ProductoDAO();
        ProductoController productController = new ProductoController(productDao);

        TipoProductoDAO tipoproductDao = new TipoProductoDAO();
        TipoProductoController tipoproductoController = new TipoProductoController(tipoproductDao);

        DetalleCompraDAO detallecompraDao = new DetalleCompraDAO();
        DetalleCompraController detallecompraController = new DetalleCompraController(detallecompraDao);

        CompraDAO compraDao = new CompraDAO();
        CompraController compraController = new CompraController(compraDao,detallecompraDao);

        ProveedorDAO proveedorDao = new ProveedorDAO();
        ProveedorController proveedorController = new ProveedorController(proveedorDao);

        TipoPagoDAO tipoPagoDao = new TipoPagoDAO();
        TipoPagoController tipoPagoController = new TipoPagoController(tipoPagoDao);

        EstadoCompraDAO estadoCompraDao = new EstadoCompraDAO();
        EstadoCompraController estadoCompraController = new EstadoCompraController(estadoCompraDao);

        // Crear y mostrar el formulario de inicio de sesión
        EventQueue.invokeLater(() -> {
            new FormLogin(loginController).setVisible(true);
            new ProductoView(productController,tipoproductoController).setVisible(true);
            new TipoProductoView(tipoproductoController).setVisible(true);
            new CompraView(productController,proveedorController,tipoPagoController,estadoCompraController,compraController).setVisible(true);
        });
    }

}

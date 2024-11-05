package org.wass.controllers;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.wass.models.CompraModel;
import org.wass.models.dao.CompraDAO;
import org.wass.models.DetalleCompraModel;
import org.wass.models.dao.DetalleCompraDAO;

/**
 * @author SamuelQ
 */
public class CompraController {
    private CompraDAO compraDao;
    @SuppressWarnings("unused")
    private DetalleCompraDAO detalleCompraDao;

    // Constructor para inicializar compraDao
    
    public CompraController() {
        this(new CompraDAO(), new DetalleCompraDAO());
    }
    @Deprecated
    public CompraController(CompraDAO compraDao, DetalleCompraDAO detalleCompraDao) {
        this.compraDao = compraDao;
        this.detalleCompraDao = detalleCompraDao;
    }

    // Método para agregar una compra
    public boolean agregarCompra(CompraModel compra,List<DetalleCompraModel> detallesCompra) throws SQLException {
        if (compra.getDescripcionCompra() == null || compra.getDescripcionCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn de la compra es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadPedida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad pedida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadRecibida() < 0) {
            JOptionPane.showMessageDialog(null, "La cantidad recibida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(compra.getCantidadRecibida() == 0){
            compra.setCantidadRecibida(0);
        }
        if (compra.getTotalCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdProveedor() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdTipoPago() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de pago", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean resultado = compraDao.agregarCompra(compra,detallesCompra);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todas las compras
    public List<CompraModel> obtenerTodasLasCompras() {
        return compraDao.obtenerTodasLasCompras();
    }

    // Método para obtener una compra
    public CompraModel obtenerCompraPorId(int idCompra) {
        return compraDao.obtenerCompraPorId(idCompra);
    }

    // Método para eliminar una compra
    public boolean eliminarCompra(int compraId) {
        boolean resultado = compraDao.eliminarCompra(compraId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public boolean actualizarCompra(CompraModel compra,int IdCompra) {
        if (compra.getDescripcionCompra() == null || compra.getDescripcionCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn de la compra es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadPedida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad pedida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getTotalCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdProveedor() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdTipoPago() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de pago", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean resultado = compraDao.actualizarCompra(compra,IdCompra);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public Integer terminarCompra(CompraModel compra, String detalleCompra, String ubicacionNueva) {

        if (compra.getDescripcionCompra() == null || compra.getDescripcionCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción de la compra es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }

        if (compra.getCantidadPedida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad pedida debe ser mayor a cero", "Inválido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (compra.getCantidadRecibida() < 0) {
            JOptionPane.showMessageDialog(null, "La cantidad recibida no puede ser negativa", "Inválido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }

        String response = compraDao.terminarCompra(compra, detalleCompra, ubicacionNueva);

        int estado = Integer.parseInt(response.substring(0, 1));
        String mensaje = response.substring(4);

        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return estado;
    }

    private String convertirListaADetalleCompraJSON(List<DetalleCompraModel> listaDetalleCompra) {
        JSONArray jsonArray = new JSONArray();

        for (DetalleCompraModel detalle : listaDetalleCompra) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idProducto", detalle.getIdProducto());
            jsonObject.put("cantidadProducto", detalle.getCantidadProducto());
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();  // Devuelve el JSON como String
    }

}

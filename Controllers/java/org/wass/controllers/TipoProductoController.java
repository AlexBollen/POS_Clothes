package org.wass.controllers;

import org.wass.models.ListDataModel;
import org.wass.models.TipoProductoModel;
import java.util.List;
import javax.swing.JOptionPane;
import org.wass.models.dao.TipoProductoDAO;

/**
 *
 * @author SamuelQ
 */
public class TipoProductoController {
    private TipoProductoDAO tipoProductoDao;

    public TipoProductoController() { this(new TipoProductoDAO()); }
    public TipoProductoController(TipoProductoDAO tipoProductoDao) {
        this.tipoProductoDao = tipoProductoDao;
    }

    // Método para agregar un tipo de producto
    public boolean agregarTipoProducto(TipoProductoModel tipoProducto) {
        if (tipoProducto.getNombreTipoProducto()== null || tipoProducto.getNombreTipoProducto().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del tipo de producto es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = tipoProductoDao.agregarTipoProducto(tipoProducto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de Producto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el tipo de producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    // Obtener una lista de los tipos de producto
    public ListDataModel<TipoProductoModel> getTiposProducto() {
        return new ListDataModel<TipoProductoModel>().addAll(obtenerTiposProducto(), (model) -> {
            return model.getNombreTipoProducto();
        });
    }

    public List<TipoProductoModel> obtenerTiposProducto() { return tipoProductoDao.obtenerTiposProducto(); }

    // Obtener el un tipo de producto por su ID
    public TipoProductoModel obtenerTipoProducto(int idTipoProducto) {
        return tipoProductoDao.obtenerTipoProductoPorId(idTipoProducto);
    }
    public boolean actualizarTipoProducto(String tipoProducto,int IdProducto) {
        if (tipoProducto == null || tipoProducto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del tipo de producto es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = tipoProductoDao.actualizarTipoProducto(tipoProducto,IdProducto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de producto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el tipo de producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    // Método para eliminar un tipo de producto
    public boolean eliminarTipoProducto(int tipoproductoId) {
        boolean resultado = tipoProductoDao.eliminarTipoProducto(tipoproductoId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}

package org.wass.controllers;

import java.util.List;
import javax.swing.JOptionPane;

import org.wass.models.dao.TipoPagoDAO;
import org.wass.models.TipoPagoModel;

/**
 *
 * @author SamuelQ
 */
public class TipoPagoController {
    private TipoPagoDAO tipoPagoDao;

    public TipoPagoController() {
        this(new TipoPagoDAO());
    }
    @Deprecated
    public TipoPagoController(TipoPagoDAO tipoPagoDao) {
        this.tipoPagoDao = tipoPagoDao;
    }


    // Método para agregar un tipo de pago
    public boolean agregarTipoPago(TipoPagoModel tipoPago) {
        if (tipoPago.getNombreTipoPago()== null || tipoPago.getNombreTipoPago().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del tipo de pago es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = tipoPagoDao.agregarTipoPago(tipoPago);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de Pago agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el el tipo de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Obtener una lista de los nombres de los tipos de pago
    public List<TipoPagoModel> obtenerTiposPago() {
        return tipoPagoDao.obtenerTiposPago();
    }


    // Obtener el un tipo de pago por su ID
    public TipoPagoModel obtenerTipoPagoPorId(int idTipoPago) {
        return tipoPagoDao.obtenerTipoPagoPorId(idTipoPago);
    }


    public boolean actualizarTipoProducto(String tipoPago,int IdTipoPago) {
        if (tipoPago == null || tipoPago.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del tipo de pago es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = tipoPagoDao.actualizarTipoPago(tipoPago,IdTipoPago);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de pago actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el tipo de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para eliminar un tipo de pago
    public boolean eliminarTipoPago(int tipoPagoId) {
        boolean resultado = tipoPagoDao.eliminarTipoPago(tipoPagoId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Tipo de pago eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el tipo de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }


    //Método para obtener un tipo de pago por su nombre
    public TipoPagoModel obtenerTipoPagoPorNombre(String nombreTipoPago) {
        return tipoPagoDao.obtenerTipoPagoPorNombre(nombreTipoPago);
    }
}

package org.wass.controllers.Sale;

/**
 *
 * @author SamuelQ
 */
import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.sale.CajaModel;
import org.wass.models.sale.CajaDAO;

public class CajaController {
    private CajaDAO cajaDao;

    // Constructor para inicializar productoDao
    public CajaController(CajaDAO productoDao) {
        this.cajaDao = cajaDao;
    }

    // Método para agregar un nuevo producto
    public boolean agregarProducto(CajaModel caja) {
        if (caja.getMontoInicial() < 0) {
            JOptionPane.showMessageDialog(null, "El monto Inicial debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (caja.getMonto() <= 0) {
            JOptionPane.showMessageDialog(null, "El monto debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (caja.getIdUsuario() <= 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un usurio válido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = cajaDao.agregarCaja(caja);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Caja agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todas las cajas
    public List<CajaModel> obtenerCajas() {
        return cajaDao.obtenerCajas();
    }

    // Método para obtener una caja
    public CajaModel obtenerCajaPorId(int idCaja) {
        return cajaDao.obtenerCajaPorId(idCaja);
    }


    // Método para eliminar una caja
    public boolean eliminarCaja(int cajaId) {
        boolean resultado = cajaDao.eliminarCaja(cajaId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Caja eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public boolean actualizarProducto(CajaModel caja,int IdCaja) {
        if (caja.getMontoInicial() < 0) {
            JOptionPane.showMessageDialog(null, "El monto Inicial debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (caja.getMonto() <= 0) {
            JOptionPane.showMessageDialog(null, "El monto debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (caja.getIdUsuario() <= 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un usurio válido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = cajaDao.actualizarCaja(caja,IdCaja);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Caja actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}

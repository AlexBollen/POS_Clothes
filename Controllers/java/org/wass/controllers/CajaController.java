package org.wass.controllers;

import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.DisplayTableModel;
import org.wass.models.TableDataModel;
import org.wass.models.RolModel;
import org.wass.models.UsuarioModel;

import org.wass.models.CajaModel;
import org.wass.models.dao.CajaDAO;

/**
 * @author SamuelQ
 */
public class CajaController {
    private CajaDAO cajaDao;

    public CajaController() {
        this(new CajaDAO());
    }
    @Deprecated
    public CajaController(CajaDAO cajaDao) {
        this.cajaDao = cajaDao;
    }

    public CajaModel obtenerCajaVendedor(UsuarioModel usuario) {
        if (usuario.getRol().getNombreRol().equals(RolModel.Tipo.Vendedor.getRol())) {            
            return cajaDao.obtenerCajaVendedor(usuario.getId());
        }
        return null;
    }
    
    // Método para agregar una nueva caja
    public boolean agregarCaja(CajaModel caja) {
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
    public TableDataModel<CajaModel> obtenerTablaCajas() {
        List<CajaModel> modelos = obtenerCajas();
        TableDataModel<CajaModel> tabla = new TableDataModel<>(new String[] {
            "ID Caja", "Monto Inicial (Apertura Caja)", "Monto Final (Cierre)",
            "Estado Caja", "Fecha Apertura", "Estado", "Usuario"
        });
        
        final DisplayTableModel<CajaModel> display = (model) -> {
            return new Object[] {
                model.getId(),
                model.getMontoInicial(),
                model.getMonto(),
                model.isEstadoCaja(),
                model.getFechaApertura(),
                model.isEstado(),
                model.getIdUsuario()
            };
        };
        for (final CajaModel m : modelos) {
            tabla.addRow(m, display);
        }
        return tabla;
    }
    public List<CajaModel> obtenerCajas() {
        return cajaDao.obtenerCajas();
    }

    // Método para obtener una caja
    public CajaModel obtenerCajaPorId(int idCaja) {
        return cajaDao.obtenerCajaPorId(idCaja);
    }

    // Método para obtener caja activa de usuario
    public CajaModel obtenerCajaUsuario(int idUsuario) { return cajaDao.obtenerCajaUsuario(idUsuario); }

    // Método para eliminar una caja
    public boolean eliminarCaja(CajaModel caja) {
        return eliminarCaja(caja.getId());
    }
    public boolean eliminarCaja(int cajaId) {
        boolean resultado = cajaDao.eliminarCaja(cajaId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Caja eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public boolean actualizarCaja(CajaModel caja) {
        return actualizarCaja(caja, caja.getId());
    }
    @Deprecated
    public boolean actualizarCaja(CajaModel caja, int idCaja) {
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
        boolean resultado = cajaDao.actualizarCaja(caja, idCaja);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Caja actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar caja", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}

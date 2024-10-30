/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers.sale;

import org.wass.models.sale.FacturaDao;
import org.wass.models.sale.FacturaModel;

import javax.swing.*;
import java.util.Objects;

/**
 *
 * @author SamuelQ
 */
public class FacturaController {
    private FacturaDao facturaDao;

    public FacturaController(FacturaDao facturaDao) { this.facturaDao = facturaDao; }

    /**
     * Método para registrar una nueva venta
     */
    public Integer agregarVenta(FacturaModel factura, String detalleVenta) {
        if (Objects.equals(detalleVenta, "[]")) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún producto", "Requerido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getNoFactura() == null || factura.getNoFactura().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El número de factura es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getTotalFactura() <= 0) {
            JOptionPane.showMessageDialog(null, "El total de la factura es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getIdCliente() != null && factura.getIdCliente() <= 0) {
            JOptionPane.showMessageDialog(null, "El id del cliente no es válido", "Inválido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getIdTipoPago() <= 0) {
            JOptionPane.showMessageDialog(null, "El id del tipo de pago no es válido", "Inválido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getIdCaja() <= 0) {
            JOptionPane.showMessageDialog(null, "El id de la caja no es válido", "Inválido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (factura.getIdSerie() == null || factura.getIdSerie().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El número de serie es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return 0;
        }

        String response = facturaDao.agregarVenta(factura, detalleVenta);
        int n = response.length();
        int estado = Integer.parseInt(response.substring(0, 1));
        String mensaje = response.substring(3, n - 3);
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else if (estado == 0) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return estado;
    }
}

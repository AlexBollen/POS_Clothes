/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.dao;

import java.sql.*;

import org.wass.controllers.db.DataBase;
import org.wass.models.FacturaModel;

/**
 * @author Alex
 */

public class FacturaDAO {

    /**
     * Método para registrar una nueva venta en la base de datos.
     *
     * @param factura Objeto FacturaModel a agregar
     * @param detalleVenta Objecto JSON con stocks y cantidades a vender
     * @return mensaje y estado que devuelve el procedimiento almacenado
     * de la base de datos
     */
    public String agregarVenta(FacturaModel factura, String detalleVenta) {
        String storedProcedure = "{call spVenta (?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DataBase.nDataBase().getConnection();
             CallableStatement statement = connection.prepareCall(storedProcedure)) {
            statement.setString(1, factura.getNoFactura());
            statement.setDouble(2, factura.getTotalFactura());
            if (factura.getIdCliente() != null)
                statement.setInt(3, factura.getIdCliente());
            else
                statement.setNull(3, Types.INTEGER);
            statement.setInt(4, factura.getIdTipoPago());
            statement.setInt(5, factura.getIdCaja());
            statement.setString(6, factura.getIdSerie());
            statement.setString(7, detalleVenta);

            statement.registerOutParameter(8, Types.VARCHAR);
            statement.registerOutParameter(9, Types.INTEGER);

            statement.execute();

            String mensaje = statement.getString(8);
            int estado = statement.getInt(9);

            return Integer.toString(estado).concat(" - " + mensaje);
        } catch (SQLException e) {
            System.err.println("Error al registrar venta: " + e.getMessage());
            return "0 - Ha sucedido un error: " + e.getMessage();
        }
    }
}
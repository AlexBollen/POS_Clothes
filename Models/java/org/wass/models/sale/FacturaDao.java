/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.sale;

import java.beans.ExceptionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.wass.controllers.db.DataBase.*;
import org.wass.models.FacturaListModel;

/**
 * @author Alex
 */

public class FacturaDao {

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

        try (var connection = nDataBase().getConnection();
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

    public List<FacturaListModel> getSales() {
        return getSales((excptn) -> {
            System.err.println("[ Error ] : No se puede listar las ventas: " + excptn.getMessage());
        });
    }

    public List<FacturaListModel> getSales(ExceptionListener exevn) {
        String sql = """
                SELECT 
                F.IdFactura,
                F.NoFactura,
                F.TotalFactura,
                P.NombrePersona,
                F.FechaFactura,
                F.IdSerie
                FROM Factura AS F
                LEFT JOIN Cliente AS C
                ON F.IdCliente = C.IdCliente
                LEFT JOIN Persona AS P
                ON C.IdPersona = P.IdPersona;
                """;

        List<FacturaListModel> ventas = new ArrayList<>();

        try (var connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet rs = statement.executeQuery()) {
                while(rs.next()) {
                    FacturaListModel factura = new FacturaListModel();
                    factura.setIdFactura(rs.getInt("IdFactura"));
                    factura.setNoFactura(rs.getString("NoFactura"));
                    factura.setTotalFactura(rs.getDouble("TotalFactura"));
                    if (rs.getString("NombrePersona") == null)
                        factura.setNombreCliente("C/F");
                    else
                        factura.setNombreCliente(rs.getString("NombrePersona"));
                    factura.setFechaFactura(rs.getDate("FechaFactura"));
                    factura.setIdSerie(rs.getString("IdSerie"));

                    ventas.add(factura);
                }
            }

        } catch (SQLException e) {
            exevn.exceptionThrown(e);
        }
        return ventas;
    }
}
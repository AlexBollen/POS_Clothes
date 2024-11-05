package org.wass.models.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.DetalleCompraModel;

public class DetalleCompraDAO {

    /**
     * Método para obtener una compra por su ID.
     *
     * @param idCompra El ID de la compra a obtener
     * @return El objeto CompraModel si se encuentra, null en caso contrario
     */
    public List<DetalleCompraModel> obtenerDetallesPorCompra(int idCompra) {
        String sql = "SELECT * FROM DetalleCompra WHERE IdCompra=?";
        List<DetalleCompraModel> detalles = new ArrayList<>();
        DetalleCompraModel detalle = null;
        
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompra);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                detalle = new DetalleCompraModel(
                        rs.getInt("idCompra"),
                        rs.getInt("idProducto"),
                        rs.getInt("CantidadProducto")
                );
                detalle.setIdDetalleCompra(rs.getInt("IdDetalleCompra"));
                detalle.setEstado(rs.getBoolean("Estado"));
                detalles.add(detalle);
            }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener detallede Compra: " + e.getMessage());
        }
        return detalles;
    }

    /**
     * Método para obtener todas las compras de la base de datos.
     *
     * @return Lista de objetos CompraModel
     */
    public List<DetalleCompraModel> obtenerTodosLosDetalles() {
        String sql = "SELECT * FROM DetalleCompra WHERE Estado=1";
        List<DetalleCompraModel> detalles = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                DetalleCompraModel detalle = new DetalleCompraModel(
                        rs.getInt("idCompra"),
                        rs.getInt("idProducto"),
                        rs.getInt("CantidadProducto")
                );
                detalle.setIdDetalleCompra(rs.getInt("IdDetalleCompra"));
                detalle.setEstado(rs.getBoolean("Estado"));
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return detalles;
    }

}

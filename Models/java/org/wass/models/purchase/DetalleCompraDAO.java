package org.wass.models.purchase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;

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

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            statement.setInt(1, idCompra);
            while (rs.next()) {
                DetalleCompraModel detalle = new DetalleCompraModel(
                        rs.getInt("idCompra"),
                        rs.getInt("idProducto"),
                        rs.getInt("CantidadProducto")
                );
                detalle.setIdDetalleCompra(rs.getInt("IdDetalleCompra")); //Setear ID del detalle
                detalle.setEstado(rs.getBoolean("Estado")); //Setear estado del detalle
                detalles.add(detalle); // Agregamos el detalle a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return detalles; // Retorna los detalles encontrados o null
    }

    /**
     * Método para obtener todas las compras de la base de datos.
     *
     * @return Lista de objetos CompraModel
     */
    public List<DetalleCompraModel> obtenerTodasLasCompras() {
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
                detalle.setIdDetalleCompra(rs.getInt("IdDetalleCompra")); //Setear ID del detalle
                detalle.setEstado(rs.getBoolean("Estado")); //Setear estado del detalle
                detalles.add(detalle); // Agregamos la compra a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return detalles; // Retorna la lista de productos
    }

}

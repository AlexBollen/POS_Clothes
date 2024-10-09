package org.wass.models.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;

/**
 *
 * @author SamuelQ
 */

public class EstadoCompraDAO {
    /**
     * Método para obtener todos los estados de compra de la base de datos.
     *
     * @return Lista de objetos EstadoCompraModel
     */
    public List<EstadoCompraModel> obtenerEstadosCompra() {
        String sql = "SELECT * FROM EstadoCompra";
        List<EstadoCompraModel> estadosCompra = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                EstadoCompraModel estadoCompra = new EstadoCompraModel(
                        rs.getString("NombreEstadoCompra")
                );
                estadoCompra.setIdEstadoCompra(rs.getInt("IdEstadoCompra"));
                estadosCompra.add(estadoCompra);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estados de compra: " + e.getMessage());
        }

        return estadosCompra;
    }


    /**
     * Método para obtener todos los NOMBRES de estados de compra de la base de datos.
     *
     * @return Lista de objetos ProductoModel
     */
    public List<String> obtenerNombreEstadoCompra() {
        String sql = "SELECT NombreEstadoCompra FROM EstadoCompra";
        List<String> estadosCompra = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                EstadoCompraModel estadoCompra = new EstadoCompraModel(
                        rs.getString("NombreEstadoCompra")
                );
                estadosCompra.add(estadoCompra.getNombreEstadoCompra());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de producto: " + e.getMessage());
        }

        return estadosCompra;
    }

    /**
     * Método para obtener UN SOLO estado de compra por su ID.
     *
     * @param id El ID del estado de compra a obtener
     * @return El objeto EstadoCompraModel si se encuentra, null en caso contrario
     */
    public List<EstadoCompraModel> obtenerUnEstadoCompraPorId(int id) {
        String sql = "SELECT * FROM EstadoCompra WHERE IdEstadoCompra = ?";
        List<EstadoCompraModel> estadosCompra = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                EstadoCompraModel tipoProducto = new EstadoCompraModel(
                    rs.getString("NombreEstadoCompra")
                );
                tipoProducto.setIdEstadoCompra(rs.getInt("IdEstadoCompra"));
                estadosCompra.add(tipoProducto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nombre del estado compra producto: " + e.getMessage());
        }

        return estadosCompra;
    }
    /**
     * Método para obtener el nombre de un estado de compra por su ID.
     *
     * @param id El ID del tipo producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public String obtenerNombreUnEstadoCompraPorId(int id) {
        String sql = "SELECT NombreEstadoCompra FROM EstadoCompra WHERE IdEstadoCompra = ?";
        String NombreEstadoCompra = "";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    NombreEstadoCompra = rs.getString("NombreEstadoCompra");
                    System.err.print(NombreEstadoCompra);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de producto: " + e.getMessage());
        }
        return NombreEstadoCompra;
    }
    /**
     * Método para obtener el nombre de un estado de compra por su ID.
     *
     * @param nombreEstadoCompra El nombre del estado de compra a obtener
     * @return El objeto EstadoCompraModel si se encuentra, null en caso contrario
     */
    public EstadoCompraModel obtenerEstadoCompraPorNombre(String nombreEstadoCompra) {
        String sql = "SELECT * FROM EstadoCompra WHERE NombreEstadoCompra = ?";
        EstadoCompraModel tipopago = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombreEstadoCompra);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    tipopago = new EstadoCompraModel(
                            rs.getString("NombreEstadoCompra")
                    );
                    tipopago.setIdEstadoCompra(rs.getInt("IdEstadoCompra"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return tipopago; // Retorna el producto encontrado o null
    }
}

package org.wass.models.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.sale.ClienteModel;

/**
 *
 * @author SamuelQ
 */

public class ClienteDAO {
    /**
     * Método para agregar un nuevo cliente a la base de datos.
     *
     * @param cliente El objeto ClienteModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarCliente(ClienteModel cliente) {
        String sql = "INSERT INTO Cliente (Nit, Estado, IdPersona)"
                + "VALUES (?, ?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNit());
            statement.setBoolean(2, true);
            statement.setInt(3, cliente.getIdPersona());

            return statement.executeUpdate() > 0; // Devuelve true si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al agregar el cliente: " + e.getMessage());
            return false; // Retorna false si hay algún error
        }
    }
    /**
     * Método para obtener todos los clientes de la base de datos.
     *
     * @return Lista de objetos ClienteModel
     */
    public List<ClienteModel> obtenerClientes() {
        String sql = "SELECT * FROM Cliente WHERE Estado = 1";
        List<ClienteModel> clientes = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ClienteModel cliente = new ClienteModel(
                        rs.getString("Nit"),
                        rs.getInt("IdPersona")
                );
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setEstado(rs.getBoolean("Estado"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }

        return clientes;
    }

    /**
     * Método para actualizar un cliente en la base de datos.
     *
     * @param cliente El objeto ClienteModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarCliente(ClienteModel cliente,int IdCaja) {
        String sql = "UPDATE Cliente SET Nit=?, IdPersona=?" +
                " WHERE IdCliente=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNit());
            statement.setInt(4, cliente.getIdPersona());
            statement.setInt(5, IdCaja);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener un cliente por su ID.
     *
     * @param id El ID del cliente a obtener
     * @return El objeto ClienteModel si se encuentra, null en caso contrario
     */
    public ClienteModel obtenerClientePorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE IdCliente = ?";
        ClienteModel cliente = null;
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                cliente = new ClienteModel(
                        rs.getString("Nit"),
                        rs.getInt("IdPersona")
                );
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setEstado(rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }

        return cliente;
    }
    /**
     * Método para eliminar un cliente por su ID.
     *
     * @param clienteId El ID del cliente a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarCliente(int clienteId) {
        String sql = "UPDATE Cliente SET Estado=0 WHERE IdCliente=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clienteId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener el cliente por su Nit.
     *
     * @param nitCliente El nit del cliente a buscar
     * @return El objeto ClienteModel si se encuentra, null en caso contrario
     */
    public ClienteModel obtenerClientePorNit(String nitCliente) {
        String sql = "SELECT * FROM Cliente WHERE Nit = ?";
        ClienteModel cliente = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nitCliente);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteModel(
                            rs.getString("Nit"),
                            rs.getInt("IdPersona")
                    );
                    cliente.setIdCliente(rs.getInt("IdCliente"));
                    cliente.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }

        return cliente;
    }
}

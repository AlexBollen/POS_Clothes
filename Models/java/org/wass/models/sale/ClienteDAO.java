package org.wass.models.sale;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.purchase.DetalleCompraModel;
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

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el cliente: " + e.getMessage());
            return false;
        }
    }


    /**
     * Método para agregar un nuevo cliente y una peresona a la base de datos.
     *
     * @param cliente El objeto ClienteModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarClientePersona(ClienteModel cliente) {
        String personaSql = "INSERT INTO Persona (NombrePersona, Direccion, Telefono, Estado)"
                         + " VALUES (?, ?, ?, ?)";
        String clienteSql = "INSERT INTO Cliente (Nit, Estado, IdPersona)"
                                + "  VALUES (?, ?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement personaStatement = connection.prepareStatement(personaSql, Statement.RETURN_GENERATED_KEYS)) {
                personaStatement.setString(1, cliente.getNombrePersona());
                personaStatement.setString(2, cliente.getDireccion());
                personaStatement.setString(3, cliente.getTelefono());
                personaStatement.setBoolean(4, true);
                personaStatement.executeUpdate();

                // Obtener el ID generado de la persona nueva
                ResultSet generatedKeys = personaStatement.getGeneratedKeys();
                int idPersona = 0;
                if (generatedKeys.next()) {
                    idPersona = generatedKeys.getInt(1); // Obtener el id que se acaba de crear
                }

                // Insertar los detalles de la compra
                try (PreparedStatement clienteStatement = connection.prepareStatement(clienteSql)) {

                        clienteStatement.setString(1, cliente.getNit());
                        clienteStatement.setBoolean(2, true);
                        clienteStatement.setInt(3, idPersona);// Asignar id que se acaba de crear
                        clienteStatement.addBatch();

                    clienteStatement.executeBatch(); // Ejecutar todos los detalles juntos
                }

                connection.commit();
                return true;

            } catch (SQLException e) {
                // Revertir la transacción si hay algún error
                connection.rollback();
                System.err.println("Error al agregar cliente: " + e.getMessage());
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * Método para obtener todos los clientes de la base de datos.
     *
     * @return Lista de objetos ClienteModel
     */
    public List<ClienteModel> obtenerClientes() {
        String sql = "SELECT persona.IdPersona, nombrePersona, direccion, telefono, nit, cliente.idCliente, cliente.estado"
                + " FROM cliente INNER JOIN persona"
                + " on Cliente.idPersona = Persona.idPersona WHERE cliente.estado = TRUE;";
        List<ClienteModel> clientes = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ClienteModel cliente = new ClienteModel(
                        rs.getInt("IdPersona"),
                        rs.getString("NombrePersona"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Nit")
                        

                );
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setEstado(rs.getBoolean("Estado"));
                cliente.setIdPersona(rs.getInt("IdPersona"));
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
    public boolean actualizarCliente(ClienteModel cliente,int idCliente) {
        String sql = "UPDATE Cliente SET Nit=?, IdPersona=?" +
                " WHERE IdCliente=?;";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNit());
            statement.setInt(2, cliente.getIdPersona());
            statement.setInt(3, idCliente);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }



    /**
     * Método para actualizar una persona y un cliente en la base de datos.
     *
     * @param cliente El objeto ClienteModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarClientePersona(ClienteModel cliente,int idCliente) {
        String sql = "UPDATE Persona p INNER JOIN Cliente c ON c.IdPersona = p.IdPersona"
                + " SET p.NombrePersona=?, p.Direccion=?, p.Telefono=?, c.Nit=?"
                + " WHERE c.IdCliente=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNombrePersona());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getNit());
            statement.setInt(5, idCliente);

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
                        rs.getInt("IdPersona"),
                        rs.getString("NombrePersona"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Nit")

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
        String sql = "UPDATE Cliente SET Estado=false WHERE IdCliente=?";

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
        String sql = "SELECT " +
                "C.IdPersona, " +
                "C.IdCliente, " +
                "P.NombrePersona, " +
                "P.Direccion, " +
                "P.Telefono, " +
                "C.Nit " +
                "FROM Cliente AS C " +
                "INNER JOIN Persona AS P " +
                "ON C.IdPersona = P.IdPersona " +
                "WHERE Nit = ?";
        ClienteModel cliente = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nitCliente);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteModel(
                            rs.getInt("IdPersona"),
                            rs.getString("NombrePersona"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Nit")
                    );
                    cliente.setIdCliente(rs.getInt("IdCliente"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
        }

        return cliente;
    }
}

package org.wass.models.purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.purchase.ProveedorModel;


/**
 *
 * @author SamuelQ
 * @author marco
 */

public class ProveedorDAO {
    /**
     * Método para agregar un nuevo proveedor a la base de datos.
     *
     * @param proveedor El objeto ProveedorModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarProveedor(ProveedorModel proveedor) {
        String sql = "INSERT INTO Proveedor (NombreComercial, Telefono2, Correo, Estado, IdPersona)"
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, proveedor.getNombreComercial());
            statement.setString(2, proveedor.getTelefono2());
            statement.setString(3, proveedor.getCorreo());
            statement.setBoolean(4, true);
            statement.setInt(5, proveedor.getIdPersona());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el proveedor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para agregar un nuevo proovedor y su persona relacionada a la base de datos.
     *
     * @param proveedor El objeto ProvedorModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarProveedorPersona(ProveedorModel proveedor) {
        String personaSql = "INSERT INTO Persona (NombrePersona, Direccion, Telefono, Estado)"
                + " VALUES (?, ?, ?, ?);";
        String proveedorSql = "INSERT INTO Proveedor (NombreComercial, Telefono2, Correo, Estado, IdPersona)"
                + "  VALUES (?, ?, ?, ?, ?);";
        
        System.out.println("aa");

        try (Connection connection = DataBase.nDataBase().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement personaStatement = connection.prepareStatement(personaSql, Statement.RETURN_GENERATED_KEYS)) {
                personaStatement.setString(1, proveedor.getNombrePersona());
                personaStatement.setString(2, proveedor.getDireccion());
                personaStatement.setString(3, proveedor.getTelefono());
                personaStatement.setBoolean(4, true);
                personaStatement.executeUpdate();

                // Obtener el ID generado de la persona nueva
                ResultSet generatedKeys = personaStatement.getGeneratedKeys();
                int idPersona = 0;
                if (generatedKeys.next()) {
                    idPersona = generatedKeys.getInt(1); // Obtener el id que se acaba de crear
                }
                
                System.out.println("id generado: "+idPersona);

                // Insertar la el nuevo proveedor
                try (PreparedStatement proveedorStatement = connection.prepareStatement(proveedorSql)) {

                    proveedorStatement.setString(1, proveedor.getNombreComercial());
                    proveedorStatement.setString(2, proveedor.getTelefono2());
                    proveedorStatement.setString(3, proveedor.getCorreo());
                    proveedorStatement.setBoolean(4, true);
                    proveedorStatement.setInt(5, idPersona);// Asignar id que se acaba de crear
                    proveedorStatement.addBatch();

                    proveedorStatement.executeBatch();
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
     * Método para obtener todos los proveedores de la base de datos.
     *
     * @return Lista de objetos ProveedorModel
     */
    public List<ProveedorModel> obtenerProveedores() {
        String sql = "SELECT IdProveedor, NombreComercial, Telefono2, Correo, Nombrepersona, Direccion, Telefono, proveedor.estado, persona.IdPersona"
                + " FROM proveedor INNER JOIN persona ON proveedor.idpersona = persona.idpersona"
                + "  WHERE proveedor.estado=true;";
        List<ProveedorModel> proveedores = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ProveedorModel proveedor = new ProveedorModel(
                        rs.getString("NombreComercial"),
                        rs.getString("Telefono2"),
                        rs.getString("Correo"),
                        rs.getInt("IdPersona"),
                        rs.getString("NombrePersona"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono")


                );
                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setEstado(rs.getBoolean("Estado"));
                proveedor.setIdPersona(rs.getInt("IdPersona"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
        }

        return proveedores;
    }

    /**
     * Método para actualizar un proveedor en la base de datos.
     *
     * @param proveedor El objeto ProveedorModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarProveedor(ProveedorModel proveedor,int idProveedor) {
        String sql = "UPDATE proveedor a INNER JOIN persona p ON a.IdPersona = p.IdPersona" +
                " SET NombreComercial=?, Telefono2=?, Correo=?, p.NombrePersona=?, p.Direccion=?, p.Telefono=?" +
                "  WHERE a.IdProveedor = ?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, proveedor.getNombreComercial());
            statement.setString(2, proveedor.getTelefono2());
            statement.setString(3, proveedor.getCorreo());
            statement.setString(4, proveedor.getNombrePersona());
            statement.setString(5, proveedor.getDireccion());
            statement.setString(6, proveedor.getTelefono());
            statement.setInt(7, idProveedor);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener un proveedor por su ID.
     *
     * @param id El ID del proveedor a obtener
     * @return El objeto ProveedorModel si se encuentra, null en caso contrario
     */
    public ProveedorModel obtenerProveedorPorId(int id) {
        String sql = "SELECT * FROM Proveedor WHERE IdProveedor = ?";
        ProveedorModel proveedor = null;
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    proveedor = new ProveedorModel(
                            rs.getString("NombreComercial"),
                            rs.getString("Telefono2"),
                            rs.getString("Correo"),
                            rs.getInt("IdPersona"),
                            rs.getString("NombrePersona"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono")
                    );
                    proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                    proveedor.setEstado(rs.getBoolean("Estado"));
                    proveedor.setIdPersona(rs.getInt("IdPersona"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor: " + e.getMessage());
        }

        return proveedor;
    }
    /**
     * Método para eliminar un proveedor por su ID.
     *
     * @param proveedorId El ID del proveedor a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarProveedor(int proveedorId) {
        String sql = "UPDATE Persona p INNER JOIN Proveedor a" +
                " on a.IdPersona = p.IdPersona" +
                " set p.estado = false, a.estado = false where a.idProveedor=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, proveedorId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener el nombre de un estado de compra por su ID.
     *
     * @param nombreProveedor El nombre del proveedor a obtener
     * @return El objeto ProveedorModel si se encuentra, null en caso contrario
     */
    public ProveedorModel obtenerProveedorPorNombre(String nombreProveedor) {
        String sql = "SELECT * FROM Proveedor WHERE NombreComercial = ?";
        ProveedorModel proveedor = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombreProveedor);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    proveedor = new ProveedorModel(
                            rs.getString("NombreComercial"),
                            rs.getString("Telefono2"),
                            rs.getString("Correo"),
                            rs.getInt("IdPersona"),
                            rs.getString("NombrePersona"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono")
                    );
                    proveedor.setIdProveedor(rs.getInt("IdTipoProducto"));
                    proveedor.setEstado(rs.getBoolean("Estado"));
                    proveedor.setIdPersona(rs.getInt("IdPersona"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor: " + e.getMessage());
        }

        return proveedor;
    }
}

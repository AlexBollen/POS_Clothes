package org.wass.models.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.purchase.ProveedorModel;

/**
 *
 * @author SamuelQ
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
     * Método para obtener todos los proveedores de la base de datos.
     *
     * @return Lista de objetos ProveedorModel
     */
    public List<ProveedorModel> obtenerProveedores() {
        String sql = "SELECT * FROM Proveedor WHERE Estado = 1";
        List<ProveedorModel> proveedores = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ProveedorModel proveedor = new ProveedorModel(
                        rs.getString("NombreComercial"),
                        rs.getString("Telefono2"),
                        rs.getString("Correo"),
                        rs.getInt("IdPersona")
                );
                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setEstado(rs.getBoolean("Estado"));
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
    public boolean actualizarProveedor(ProveedorModel proveedor,int IdProveedor) {
        String sql = "UPDATE Proveedor SET NombreComercial=?, Telefono2=?, Correo=?, IdPersona=?" +
                " WHERE IdProveedor=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, proveedor.getNombreComercial());
            statement.setString(2, proveedor.getTelefono2());
            statement.setString(3, proveedor.getCorreo());
            statement.setInt(4, proveedor.getIdPersona());
            statement.setInt(5, IdProveedor);

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
                            rs.getInt("IdPersona")
                    );
                    proveedor.setIdProveedor(rs.getInt("IdTipoProducto"));
                    proveedor.setEstado(rs.getBoolean("Estado"));
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
        String sql = "UPDATE Proveedor SET Estado=0 WHERE IdProveedor=?";

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
                            rs.getInt("IdPersona")
                    );
                    proveedor.setIdProveedor(rs.getInt("IdTipoProducto"));
                    proveedor.setEstado(rs.getBoolean("Estado"));
                }
            }
            /*
                try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoModel(
                            rs.getString("NombreProducto"),
                            rs.getFloat("PrecioCosto"),
                            rs.getFloat("PrecioVenta"),
                            rs.getFloat("DescuentoPorcentual"),
                            rs.getInt("IdTipoProducto")
                    );
                    producto.setIdProducto(rs.getInt("IdProducto")); // Seteamos el ID del producto porque no está en constructor
                    producto.setEstado(rs.getBoolean("Estado")); // También seteamos el Estado
                }
            }
             */
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return proveedor;
    }
}

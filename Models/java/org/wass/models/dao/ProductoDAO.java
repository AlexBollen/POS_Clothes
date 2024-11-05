package org.wass.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.ProductoModel;

/**
 * Clase para manejar las operaciones de acceso a datos de productos.
 *
 * @author SamuelQ
 */
public class ProductoDAO {
    /**
     * Método para agregar un nuevo producto a la base de datos.
     *
     * @param producto El objeto ProductoModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarProducto(ProductoModel producto) {
        String sql = "INSERT INTO Producto (NombreProducto, PrecioCosto, PrecioVenta, DescuentoPorcentual, IdTipoProducto, Estado)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombreProducto());
            statement.setFloat(2, producto.getPrecioCosto());
            statement.setFloat(3, producto.getPrecioVenta());
            statement.setFloat(4, producto.getDescuentoPorcentual());
            statement.setInt(5, producto.getIdTipoProducto());
            statement.setBoolean(6, true);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
            return false; // Retorna false si hay algún error
        }
    }

    /**
     * Método para obtener un producto por su ID.
     *
     * @param idProducto El ID del producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public ProductoModel obtenerProductoPorId(int idProducto) {
        String sql = "SELECT * FROM Producto WHERE IdProducto=?";
        ProductoModel producto = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idProducto);

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
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return producto; // Se retorna el producto encontrado o null
    }

    /**
     * Método para obtener todos los productos de la base de datos.
     *
     * @return Lista de objetos ProductoModel
     */
    public List<ProductoModel> obtenerTodosLosProductos() {
        //Se seleccionan los Productos con Estado 1, el Estado nos indica si está activo o inactivo
        String sql = "SELECT * FROM Producto WHERE Estado=1";
        List<ProductoModel> productos = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                ProductoModel producto = new ProductoModel(
                        rs.getString("NombreProducto"),
                        rs.getFloat("PrecioCosto"),
                        rs.getFloat("PrecioVenta"),
                        rs.getFloat("DescuentoPorcentual"),
                        rs.getInt("IdTipoProducto")
                );
                producto.setIdProducto(rs.getInt("IdProducto")); // Seteamos el ID del producto porque no está en constructor
                producto.setEstado(rs.getBoolean("Estado")); // También seteamos el estado
                productos.add(producto); // Agregamos el producto a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return productos; // Retorna la lista de productos
    }
    /**
     * Método para agregar un nuevo producto a la base de datos.
     *
     * @param producto El objeto ProductoModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarProducto(ProductoModel producto,int IdProducto) {
        // Se actualizan todos los atributos, a excepción del IdProducto y el Estado
        String sql = "UPDATE Producto SET NombreProducto=?, PrecioCosto=?, PrecioVenta=?, DescuentoPorcentual=?, IdTipoProducto=? WHERE IdProducto=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombreProducto());
            statement.setFloat(2, producto.getPrecioCosto());
            statement.setFloat(3, producto.getPrecioVenta());
            statement.setFloat(4, producto.getDescuentoPorcentual());
            statement.setInt(5, producto.getIdTipoProducto());
            statement.setInt(6, IdProducto);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para eliminar un producto por su ID.
     *
     * @param productoId El ID del producto a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarProducto(int productoId) {
        //NO SE ELIMINA, solo se setea el Estado en 0 para que no sea visible
        String sql = "UPDATE Producto SET Estado=0 WHERE IdProducto=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productoId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener un producto por su ID.
     *
     * @param Nombre El ID del producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public ProductoModel obtenerDatosDeProductosPorNombre(String Nombre) {
        String sql = "SELECT * FROM Producto WHERE NombreProducto=?";
        ProductoModel producto = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, Nombre);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoModel(
                            rs.getString("NombreProducto"),
                            rs.getFloat("PrecioCosto"),
                            rs.getFloat("PrecioVenta"),
                            rs.getFloat("DescuentoPorcentual"),
                            rs.getInt("IdTipoProducto")
                    );
                    producto.setIdProducto(rs.getInt("IdProducto")); // Seteamos el ID del producto ya que no están en el constructor
                    producto.setEstado(rs.getBoolean("Estado")); // También seteamos el Estado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return producto; //Devuelve el producto encontrado o null
    }
}



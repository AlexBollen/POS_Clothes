package org.wass.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.TipoProductoModel;

/**
 *
 * @author SamuelQ
 */
public class TipoProductoDAO {
    /**
     * Método para agregar un nuevo tipo de producto a la base de datos.
     *
     * @param tipoproducto El objeto TipoProductoModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarTipoProducto(TipoProductoModel tipoproducto) {
        String sql = "INSERT INTO TipoProducto (NombreTipoProducto, Estado)"
                + "VALUES (?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoproducto.getNombreTipoProducto());
            statement.setBoolean(2, tipoproducto.isEstado());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el tipo producto: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener todos los tipos productos de la base de datos.
     *
     * @return Lista de objetos ProductoModel
     */
    public List<TipoProductoModel> obtenerTiposProducto() {
        String sql = "SELECT * FROM TipoProducto WHERE Estado = 1";
        List<TipoProductoModel> tiposProducto = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                TipoProductoModel tipoProducto = new TipoProductoModel(
                        rs.getString("NombreTipoProducto"),
                        rs.getBoolean("Estado")
                );
                tipoProducto.setIdTipoProducto(rs.getInt("IdTipoProducto"));
                tiposProducto.add(tipoProducto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de producto: " + e.getMessage());
        }

        return tiposProducto;
    }

    /**
     * Método para agregar un nuevo tipo producto a la base de datos.
     *
     * @param tipoProducto El objeto ProductoModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarTipoProducto(String tipoProducto,int IdProducto) {
        String sql = "UPDATE TipoProducto SET NombreTipoProducto=?" +
                " WHERE IdTipoProducto=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoProducto);
            statement.setInt(2, IdProducto);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener un tipo de producto por su ID.
     *
     * @param id El ID del tipo producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public TipoProductoModel obtenerTipoProductoPorId(int id) {
        String sql = "SELECT * FROM TipoProducto WHERE IdTipoProducto = ?";
        TipoProductoModel tipoProducto = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    tipoProducto = new TipoProductoModel(
                            rs.getString("NombreTipoProducto"),
                            rs.getBoolean("Estado")
                    );
                    tipoProducto.setIdTipoProducto(rs.getInt("IdTipoProducto"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de producto: " + e.getMessage());
        }

        return tipoProducto;
    }

    /**
     * Método para eliminar un tipo de producto por su ID.
     *
     * @param tipoproductoId El ID del producto a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarTipoProducto(int tipoproductoId) {
        String sql = "UPDATE TipoProducto SET Estado=0 WHERE IdTipoProducto=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tipoproductoId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo de producto: " + e.getMessage());
            return false;
        }
    }
}


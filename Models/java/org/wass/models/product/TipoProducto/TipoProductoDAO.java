/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.product.TipoProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.product.TipoProductoModel;

/**
 *
 * @author SamuelQ
 */
public class TipoProductoDAO {
    /**
     * Método para agregar un nuevo producto a la base de datos.
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
            statement.setBoolean(2, tipoproducto.getEstado());

            return statement.executeUpdate() > 0; // Devuelve true si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al agregar el tipo producto: " + e.getMessage());
            return false; // Retorna false si hay algún error
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
     * Método para agregar un nuevo producto a la base de datos.
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

            return statement.executeUpdate() > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener todos los NOMBRES tipos productos de la base de datos.
     *
     * @return Lista de objetos ProductoModel
     */
    public List<String> obtenerNombreTiposProducto() {
        String sql = "SELECT NombreTipoProducto FROM TipoProducto WHERE Estado = 1";
        List<String> tiposProducto = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                TipoProductoModel tipoProducto = new TipoProductoModel(
                        rs.getString("NombreTipoProducto"),
                        rs.getBoolean("Estado")
                );
                tiposProducto.add(tipoProducto.getNombreTipoProducto());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de producto: " + e.getMessage());
        }

        return tiposProducto;
    }

    /**
     * Método para obtener un producto por su ID.
     *
     * @param id El ID del tipo producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public List<TipoProductoModel> obtenerTipoProductoPorId(int id) {
        String sql = "SELECT * FROM TipoProducto WHERE IdTipoProducto = ?";
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
     * Método para obtener el nombre de un producto por su ID.
     *
     * @param id El ID del tipo producto a obtener
     * @return El objeto ProductoModel si se encuentra, null en caso contrario
     */
    public String obtenerNombreTipoProductoPorId(int id) {
        String sql = "SELECT NombreTipoProducto FROM TipoProducto WHERE IdTipoProducto = ?";
        String NombreTipoProducto = "";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    NombreTipoProducto = rs.getString("NombreTipoProducto");
                    System.err.print(NombreTipoProducto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de producto: " + e.getMessage());
        }
        return NombreTipoProducto;
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
            return statement.executeUpdate() > 0; // Devuelve true si se eliminó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false; // Retorna false si hay algún error
        }
    }
}


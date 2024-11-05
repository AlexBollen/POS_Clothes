/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.wass.controllers.db.DataBase;
import org.wass.models.RolModel;

/**
 * Clase para manejar las operaciones de acceso a datos de roles.
 *
 * @author Alex
 */
public class RolDAO {
    /**
     * Método para obtener un rol por su ID.
     *
     * @param idRol ID del rol a obtener
     * @return Objeto RolModel si se encuentra, null en caso contrario
     */
    public RolModel obtenerRol(int idRol) {
        String sql = "SELECT * FROM Rol WHERE IdRol = ?";
        RolModel rol = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idRol);

            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    rol = new RolModel(
                            rs.getInt("IdRol"),
                            rs.getString("NombreRol")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rol: " + e.getMessage());
        }
        return rol;
    }

    /**
     * Método para obtener todos los roles activos
     *
     * @return Lista de objetos RolModel
     */
    public List<RolModel> obtenerRoles() {
        // Se seleccionan solo los roles con Estado 1 (activos)
        String sql = "SELECT * FROM Rol WHERE Estado = 1";
        List<RolModel> roles = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                RolModel rol = new RolModel(
                        rs.getInt("IdRol"),
                        rs.getString("NombreRol")
                );
                roles.add(rol);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener roles: " + e.getMessage());
        }
        return roles; // Retornar lista de roles
    }

    /**
     *  Método para agregar un nuevo rol a la base de datos
     *
     * @param rol Objeto RolModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarRol(RolModel rol) {
        String sql = "INSERT INTO Rol (NombreRol)"
                + "VALUES (?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rol.getNombreRol());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar el rol: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para actualizar un rol
     *
     * @param rol Objeto RolModel a actualizar
     * @param idRol ID del rol a actualizar
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean actualizarRol(RolModel rol, int idRol) {
        // Se actualiza el único campo posible
        String sql = "UPDATE Rol SET NombreRol = ? WHERE IdRol = ?";
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rol.getNombreRol());
            statement.setInt(2, idRol);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para eliminar lógicamente un rol
     *
     * @param idRol ID del rol a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarRol(int idRol) {
        // Setear Estado en 0 para que no sea visible
        String sql = "UPDATE Rol SET Estado = 0 WHERE IdRol = ?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idRol);

            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar rol: " + e.getMessage());
            return false;
        }
    }
}

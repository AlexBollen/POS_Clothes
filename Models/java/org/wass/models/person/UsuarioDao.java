package org.wass.models.person;

import java.beans.ExceptionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.wass.controllers.ControllerException;
import static org.wass.controllers.db.DataBase.*;
import static org.wass.models.utilities.Cipher.*;

/**
 * @author Alex
 * @author wil
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioDao {
    
    public boolean agregarUsuario(UsuarioModel model) {
        String sql = """
                     INSERT INTO Usuario (NombreUsuario, Contrasenia, Estado, IdPersona, IdRol) 
                     VALUES (?, ?, ?, ?, ?) 
                     """;
        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, model.getNombreUsuario());
            statement.setBytes(2, nBCryptGenerateNewRandomHash(model.getContrasenia()));
            statement.setBoolean(3, model.getEstado());
            statement.setInt(4, model.getIdPersona());
            statement.setInt(5, model.getRol().getIdRol());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error crear un nuevo usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarUsuario(UsuarioModel model, boolean todo) {
        String sql = todo 
                ? """
                     UPDATE Usuario SET NombreUsuario=?,Contrasenia=?,
                     Estado=?,IdPersona=?,IdRol=? WHERE IdUsuario=?
                     """
                : """
                    UPDATE Usuario SET NombreUsuario=?,
                    Estado=?,IdPersona=?,IdRol=? WHERE IdUsuario=?
                  """;
        
        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            if (todo) {
                statement.setString(1, model.getNombreUsuario());
                statement.setBytes(2, nBCryptGenerateNewRandomHash(model.getContrasenia()));
                statement.setBoolean(3, model.getEstado());
                statement.setInt(4, model.getIdPersona());
                statement.setInt(5, model.getRol().getIdRol());
                statement.setInt(6, model.getIdUsuario());
            } else {
                statement.setString(1, model.getNombreUsuario());
                statement.setBoolean(2, model.getEstado());
                statement.setInt(3, model.getIdPersona());
                statement.setInt(4, model.getRol().getIdRol());
                statement.setInt(5, model.getIdUsuario());
            }
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }
    
    public List<UsuarioModel> getUsuarios() {
        return getUsuarios((excptn) -> {
            System.err.println("[ Error ] :No se puede listar los usuarios: " + excptn.getMessage());
        });
    }
    public List<UsuarioModel> getUsuarios(ExceptionListener exevn) {
        List<UsuarioModel> usuarios = new ArrayList<>();
        String sql = """
                     SELECT U.IdUsuario, U.NombreUsuario, U.Estado AS EstadoUsuario,
                     P.IdPersona, P.NombrePersona, P.Direccion, P.Telefono, P.Estado AS EstadoPersona,
                     R.IdRol, R.NombreRol, R.Estado AS EstadoRol  FROM Usuario U 
                     INNER JOIN Persona P ON U.IdPersona = P.IdPersona 
                     INNER JOIN Rol R ON U.IdRol = R.IdRol;
                     """;
        
        try (var connetion = nDataBase().getConnection();
                PreparedStatement query = connetion.prepareStatement(sql)) {
            
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    RolModel rol = new RolModel();
                    rol.setIdRol(rs.getInt("IdRol"));
                    rol.setNombreRol(rs.getString("NombreRol"));
                    rol.setEstado(rs.getBoolean("EstadoRol"));
                    
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setContrasenia(null);
                    usuario.setDireccion(rs.getString("Direccion"));
                    usuario.setEstado(rs.getBoolean("EstadoUsuario"));
                    usuario.setIdPersona(rs.getInt("IdPersona"));
                    usuario.setIdUsuario(rs.getInt("IdUsuario"));
                    usuario.setNombrePersona(rs.getString("NombrePersona"));
                    usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                    usuario.setTelefono(rs.getString("Telefono"));
                    usuario.setRol(rol);
                    
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException  ex) {
            exevn.exceptionThrown(ex);
        }
        return usuarios;
    }
    
    /**
    * Método para validar credenciales de usuario en la base de datos.
    * 
    * @param username Nombre de usuario
    * @param password Contraseña
    * @return el usuario encontrado con credenciales validas, null en caso contrario
    */
    public UsuarioModel validateCredentials(String username, String password) {
        String sql = "SELECT U.*, P.*, R.NombreRol FROM Usuario AS U " +
                     "INNER JOIN Persona AS P " +
                     "ON P.IdPersona = U.IdPersona " +
                     "INNER JOIN Rol AS R " +
                     "ON R.IdRol = U.IdRol " +
                     "WHERE U.NombreUsuario=?";
        UsuarioModel us = null;
        
        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, username);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Lógica de validación de contraseña
                    byte[] storedHash = rs.getBytes("Contrasenia");
                    Boolean result = nBCryptVerifyerRandomHash(storedHash, password);
                    
                    if (result) {
                        // Crear objeto Rol para posterior asignación
                        RolModel rolModel = new RolModel();
                        rolModel.setIdRol(rs.getInt("IdRol"));
                        rolModel.setNombreRol(rs.getString("NombreRol"));

                        // Crear objeto Usuario 
                        us = new UsuarioModel();
                        us.setIdPersona(rs.getInt("IdPersona"));
                        us.setNombrePersona(rs.getString("NombrePersona"));
                        us.setDireccion(rs.getString("Direccion"));
                        us.setTelefono(rs.getString("Telefono"));
                        us.setEstado(rs.getBoolean("Estado"));
                        us.setIdUsuario(rs.getInt("IdUsuario"));
                        us.setNombreUsuario(rs.getString("NombreUsuario"));
                        us.setRol(rolModel);
                    }
                }
            }
        } catch (SQLException | ControllerException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return us;
    }
}

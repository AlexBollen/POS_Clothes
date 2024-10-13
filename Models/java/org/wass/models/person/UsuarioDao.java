package org.wass.models.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wass.controllers.ControllerException;
import org.wass.controllers.db.DataBase;
import static org.wass.models.utilities.Cipher.*;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioDao {
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
        
        try (Connection connection = DataBase.nDataBase().getConnection();
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

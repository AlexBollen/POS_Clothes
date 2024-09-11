package org.wass.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wass.controllers.ControllerException;
import org.wass.controllers.db.DataBase;
import static org.wass.models.utilities.CipherUtilities.*;

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
    public Usuario validateCredentials(String username, String password) {
        String sql = "SELECT U.*, P.*, R.NombreRol FROM usuario AS U " +
                     "INNER JOIN persona AS P " +
                     "ON P.IdPersona = U.IdPersona " +
                     "INNER JOIN rol AS R " +
                     "ON R.IdRol = U.IdRol " +
                     "WHERE U.NombreUsuario=?";
        Usuario us = null;
        
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
                        Rol rol = new Rol();
                        rol.setIdRol(rs.getInt("IdRol"));
                        rol.setNombreRol(rs.getString("NombreRol"));

                        // Crear objeto Usuario 
                        us = new Usuario();
                        us.setIdPersona(rs.getInt("IdPersona"));
                        us.setNombrePersona(rs.getString("NombrePersona"));
                        us.setDireccion(rs.getString("Direccion"));
                        us.setTelefono(rs.getString("Telefono"));
                        us.setEstado(rs.getBoolean("Estado"));
                        us.setIdUsuario(rs.getInt("IdUsuario"));
                        us.setNombreUsuario(rs.getString("NombreUsuario"));
                        us.setRol(rol);
                    }
                }
            }
        } catch (SQLException | ControllerException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
        }
        return us;
    }
}

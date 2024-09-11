package org.wass.controllers;

import javax.swing.JOptionPane;
import org.wass.models.Usuario;
import org.wass.models.UsuarioDao;

/**
 * Controlador para manejar la validación de inicio de sesión.
 * 
 * @author Alex
 */
public class LoginController {
    private Usuario user;
    private UsuarioDao userDao;

    // Constructor para inicializar userDao
    public LoginController(UsuarioDao userDao) {
        this.userDao = userDao;
    }
    
    public boolean validateLogin(String username, String password) {
        if (username.isEmpty() || username.equals("Ingrese su nombre de usuario")) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario es requerido");
            return false;
        }
        if (password.isEmpty() || password.equals("********")) {
            JOptionPane.showMessageDialog(null, "La contraseña es requerida");
            return false;
        }
        user = userDao.validateCredentials(username, password);
        if (user != null && user.getNombreUsuario() != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Las credenciales no son válidas");
            return false;
        }
    }
}
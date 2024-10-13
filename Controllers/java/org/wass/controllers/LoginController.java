package org.wass.controllers;

import javax.swing.JOptionPane;
import org.wass.models.person.UsuarioModel;
import org.wass.models.person.UsuarioDao;

/**
 * Controlador para manejar la validación de inicio de sesión.
 * 
 * @author Alex
 */
public class LoginController {
    private UsuarioModel user;
    private UsuarioDao userDao;

    // Constructor para inicializar userDao
    public LoginController(UsuarioDao userDao) {
        this.userDao = userDao;
    }
    
    public UsuarioModel validateLogin(String username, String password) {
        if (username.isEmpty() || username.equals("Ingrese su nombre de usuario")) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (password.isEmpty() || password.equals("********")) {
            JOptionPane.showMessageDialog(null, "La contraseña es requerida","Requerido", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        user = userDao.validateCredentials(username, password);
        if (user != null && user.getNombreUsuario() != null) {
            return user;
        } else {
            JOptionPane.showMessageDialog(null, "Las credenciales no son válidas","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.controllers;

import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.ListDataModel;
import org.wass.models.RolModel;
import org.wass.models.dao.RolDAO;

/**
 *
 * @author Alex
 */
public class RolController {
    private RolDAO rolDao;

    // Constructor para inicializar rolDao
    
    public RolController() { 
        this(new RolDAO());
    }
    public RolController(RolDAO rolDao) {
        this.rolDao = rolDao;
    }

    /**
     * Método para obtener un rol
     *
     * @param idRol ID del rol a obtener
     * @return modelo
     */
    public RolModel obtenerRol(int idRol) {
        return rolDao.obtenerRol(idRol);
    }

    /**
     * Método para obtener todos los roles
     * @return lista modelo
     */
    public List<RolModel> obtenerRoles() {
        return rolDao.obtenerRoles();
    }
    
    /**
     * Método para obtener todos los roles destinado a un componente Swinf/AWT.
     * @return lista roles
     */
    public ListDataModel<RolModel> obtenerTodoRoles() {
        return new ListDataModel<RolModel>().addAll(obtenerRoles(), (model) -> {
            return model.getNombreRol();
        });
    }

    /**
     * Método para agregar un nuevo rol
     *
     * @param rol Objeto RolModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarRol(RolModel rol) {
        if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del rol es requerido");
            return false;
        }
        boolean result = rolDao.agregarRol(rol);
        if (result) {
            JOptionPane.showMessageDialog(null, "Rol agregado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el rol");
        }
        return result;
    }

    /**
     * Método para actualizar un rol
     *
     * @param rol Objeto RolModel con datos a actualizar
     * @param idRol ID del rol a actualizar
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean actualizarRol(RolModel rol, int idRol) {
        // Verificación de que el rol existe
        RolModel existingRole = rolDao.obtenerRol(idRol);
        if (existingRole == null) {
            JOptionPane.showMessageDialog(null, "El rol no existe");
            return false;
        }
        if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del rol es requerido");
            return false;
        }
        boolean result = rolDao.actualizarRol(rol, idRol);
        if (result) {
            JOptionPane.showMessageDialog(null, "Rol actualizado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el rol");
        }
        return result;
    }

    /**
     * Método para eliminar lógicamente un rol
     *
     * @param idRol ID del rol a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarRol(int idRol) {
        // Verificación de que el rol existe
        RolModel existingRole = rolDao.obtenerRol(idRol);
        if (existingRole == null) {
            JOptionPane.showMessageDialog(null, "El rol no existe");
            return false;
        }
        boolean result = rolDao.eliminarRol(idRol);
        if (result) {
            JOptionPane.showMessageDialog(null, "Rol eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el rol");
        }
        return result;
    }
}

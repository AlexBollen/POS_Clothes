package org.wass.controllers;

import java.beans.ExceptionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.wass.models.TableDataModel;
import org.wass.models.person.UsuarioDao;
import org.wass.models.person.UsuarioModel;

/**
 * @author wil
 */
public class UserController {
    
    private final UsuarioDao dao;
    private ExceptionListener exceptionListener;
    
    public UserController() {
        this.dao = new UsuarioDao();
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }
    
    public boolean actualizar(UsuarioModel model, boolean ncontra) {
        return dao.actualizarUsuario(model, ncontra);
    }
    
    public boolean nuevo(UsuarioModel model) {
        return dao.agregarUsuario(model);
    }
    
    public DefaultTableModel getTablaUsuario() {
        List<UsuarioModel> models =  exceptionListener == null 
                                    ? dao.getUsuarios() 
                                    : dao.getUsuarios(exceptionListener);
        TableDataModel<UsuarioModel> dataModel = new TableDataModel<>(new String[] {
            "ID Usuario", "Nombre Usuario", "Estado", "Rol"
        });
        for (final UsuarioModel m : models) {
            dataModel.addRow(m, (UsuarioModel model) -> {
                return new Object[] {
                    model.getIdUsuario(),
                    model.getNombreUsuario(),
                    model.getEstado(),
                    model.getRol().getNombreRol()
                };
            });
        }
        return dataModel;
    }
    
    public void actualizar(UsuarioModel model) {
        
    }
}

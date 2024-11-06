package org.wass.controllers;

import java.beans.ExceptionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.wass.models.ListDataModel;
import org.wass.models.TableDataModel;
import org.wass.models.dao.UsuarioDAO;
import org.wass.models.UsuarioModel;

/**
 * @author wil
 */
public class UserController {
    
    private final UsuarioDAO dao;
    private ExceptionListener exceptionListener;
    
    public UserController() {
        this.dao = new UsuarioDAO();
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
    
    public ListDataModel<UsuarioModel> getListaUsuario() {
        List<UsuarioModel> modelos = dao.getUsuarios();
        ListDataModel<UsuarioModel> lista = new ListDataModel<>();        
        return lista.addAll(modelos, (UsuarioModel model) -> {
            return model.getNombreUsuario();
        });
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
}

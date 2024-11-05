package org.wass.controllers;

import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.ListDataModel;
import org.wass.models.dao.PersonaDAO;
import org.wass.models.PersonaModel;

/**
 * @author marco
 */
public class PersonaController {
    
    private PersonaDAO personaDao;
     
    // Constructor para inicializar personaDao
    public PersonaController() {
        this(new PersonaDAO());
    }
    public PersonaController(PersonaDAO personaDao) {
        this.personaDao = personaDao;
    }
    
    // Método para agregar una nueva persona
    public boolean agregarPersona(PersonaModel persona) {
        
        if (persona.getNombrePersona() == null || persona.getNombrePersona().isEmpty()){
            return false;
        }
        
        if (persona.getDireccion() == null || persona.getDireccion().isEmpty()){
            persona.setDireccion("");
        }
        
        if(persona.getTelefono() == null || persona.getTelefono().isEmpty()){
            persona.setTelefono("");
        }
        
        boolean resultado = personaDao.agregarPersona(persona);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Persona agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar la persona", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    
    // Obtener una lista de las personas
    public ListDataModel<PersonaModel> obtenerTodoPersonas() {
        return new ListDataModel<PersonaModel>().addAll(obtenerPersonas(), (PersonaModel model) -> {
            return model.getNombrePersona();
        });
    }
    public List<PersonaModel> obtenerPersonas() {
        return personaDao.obtenerPersonas();
    }
    
    // Obtener el un tipo de producto por su ID
    public PersonaModel obtenerPersona(int idPersona) {
        return personaDao.obtenerPersonaPorId(idPersona);
    }
    
    public boolean actualizarPersona(PersonaModel persona,int idPersona) {
       if (persona.getNombrePersona() == null || persona.getNombrePersona().isEmpty()) {
           JOptionPane.showMessageDialog(null, "El nombre de la persona es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
           return false;
       }

       boolean resultado = personaDao.actualizarPersona(persona,idPersona);
       if (resultado) {
           JOptionPane.showMessageDialog(null, "Persona actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
       } else {
           JOptionPane.showMessageDialog(null, "Error al actualizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
       }
       return resultado;
    }
   
     // Método para eliminar un tipo de producto
    public boolean eliminarPersona(int personaId) {
        boolean resultado = personaDao.eliminarPersona(personaId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Persona eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar la persona", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }    
}

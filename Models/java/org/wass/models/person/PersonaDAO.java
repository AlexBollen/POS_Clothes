
package org.wass.models.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;

/**
 *
 * @author marco
 */
public class PersonaDAO {
    
     /**
     * Método para agregar una nueva persona a la base de datos.
     *
     * @param persona El objeto Persona a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    
    
     public boolean agregarPersona(PersonaModel persona) {
        String sql = "INSERT INTO Persona (NombrePersona, Direccion, Telefono, Estado)"
                + "VALUES (?, ?, ?, ?)";


        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, persona.getNombrePersona());
            statement.setString(2, persona.getDireccion());
            statement.setString(3, persona.getTelefono());
            statement.setBoolean(4, true);
            

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar la persona: " + e.getMessage());
            return false;
        }
    }
     
     /**
     * Método para obtener todas personas de la base de datos.
     *
     * @return Lista de objetos PersonaModel
     */
    public List<PersonaModel> obtenerPersonas() {
        String sql = "SELECT * FROM Persona WHERE Estado = 1";
        List<PersonaModel> personas = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                PersonaModel persona = new PersonaModel(
                        rs.getInt("IdPersona"),
                        rs.getString("NombrePersona"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getBoolean("Estado")
                );
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de producto: " + e.getMessage());
        }

        return personas;
    }
     
     /**
     * Método para actualizar una persona en la base de datos.
     *
     * @param persona El objeto PersonaModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarPersona(PersonaModel persona,int idPersona) {
        String sql = "UPDATE Persona SET NombrePersona=?, Direccion=?, Telefono=?" +
                " WHERE IdPersona=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, persona.getNombrePersona());
            statement.setString(2, persona.getDireccion());
            statement.setString(3, persona.getTelefono());
            statement.setInt(4, idPersona);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la persona: " + e.getMessage());
            return false;
        }
    }
    
    
     /**
     * Método para obtener una persona por su ID.
     *
     * @param id El ID de la persona a obtener
     * @return El objeto Persona si se encuentra, null en caso contrario
     */
     public PersonaModel obtenerPersonaPorId(int id) {
        String sql = "SELECT * FROM Persona WHERE IdPersona = ?";
        PersonaModel persona = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    persona = new PersonaModel(
                            rs.getInt("IdPersona"),
                            rs.getString("NombrePersona"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getBoolean("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la persona: " + e.getMessage());
        }

        return persona;
    }
     
     
      /**
     * Método para eliminar una persona por su ID.
     *
     * @param personaId El ID de la persona a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarPersona(int personaId) {
        String sql = "UPDATE Persona SET Estado=0 WHERE IdPersona=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, personaId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la persona: " + e.getMessage());
            return false;
        }
    }
     
     
    
}

package org.wass.models.person;

import org.wass.models.Model;

/**
 *
 * @author Alex
 * @version 1.0.1
 * @since 1.0.0
 */
public class PersonaModel implements Model {
    private int IdPersona;
    private String NombrePersona;
    private String Direccion;
    private String Telefono;
    private Boolean Estado;
    
    PersonaModel() {

    }

    public PersonaModel(int IdPersona, String NombrePersona, String Direccion, String Telefono, Boolean Estado) {
        this.IdPersona = IdPersona;
        this.NombrePersona = NombrePersona;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Estado = Estado;
    }

    public PersonaModel(int IdPersona, String NombrePersona, String Direccion, String Telefono) {
        this(IdPersona, NombrePersona, Direccion, Telefono, true);

    }

    @Override
    public int getId() {
        return getIdPersona();
    }
    
    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public String getNombrePersona() {
        return NombrePersona;
    }

    public void setNombrePersona(String NombrePersona) {
        this.NombrePersona = NombrePersona;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }
    
    
}

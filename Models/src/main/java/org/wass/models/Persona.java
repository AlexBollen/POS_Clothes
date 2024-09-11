package org.wass.models;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class Persona {
    private int IdPersona;
    private String NombrePersona;
    private String Direccion;
    private String Telefono;
    private Boolean Estado;
    
    public Persona() {
        
    }

    public Persona(int IdPersona, String NombrePersona, String Direccion, String Telefono, Boolean Estado) {
        this.IdPersona = IdPersona;
        this.NombrePersona = NombrePersona;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Estado = Estado;
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
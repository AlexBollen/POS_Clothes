package org.wass.models;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class Usuario extends Persona {
    private int IdUsuario;
    private String NombreUsuario;
    private String Contrasenia;
    private Rol Rol;

    public Usuario(){
        
    }
    
    public Usuario(int IdPersona, String NombrePersona, String Direccion, String Telefono, Boolean Estado, int IdUsuario, String NombreUsuario, String Contrasenia, Rol Rol) {
        super(IdPersona, NombrePersona, Direccion, Telefono, Estado);
        this.IdUsuario = IdUsuario;
        this.NombreUsuario = NombreUsuario;
        this.Contrasenia = Contrasenia;
        this.Rol = Rol;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }
}
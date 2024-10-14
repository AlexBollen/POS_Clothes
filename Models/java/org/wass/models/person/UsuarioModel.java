package org.wass.models.person;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioModel extends PersonaModel {
    private int IdUsuario;
    private String NombreUsuario;
    private String Contrasenia;
    private RolModel RolModel;

    public UsuarioModel(){
        
    }
    
    public UsuarioModel(int IdPersona, String NombrePersona, String Direccion, String Telefono, Boolean Estado, int IdUsuario, String NombreUsuario, String Contrasenia, RolModel RolModel) {
        super(IdPersona, NombrePersona, Direccion, Telefono, Estado);
        this.IdUsuario = IdUsuario;
        this.NombreUsuario = NombreUsuario;
        this.Contrasenia = Contrasenia;
        this.RolModel = RolModel;
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

    public RolModel getRol() {
        return RolModel;
    }

    public void setRol(RolModel RolModel) {
        this.RolModel = RolModel;
    }
}
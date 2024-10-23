package org.wass.models.person;

/**
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class UsuarioModel extends PersonaModel {
    
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private RolModel rolModel;

   
    public UsuarioModel() { }
    public UsuarioModel(int idPersona, String nombrePersona, String direccion, String telefono, 
            boolean estado, int idUsuario, String nombreUsuario, String contrasenia, RolModel rolModel) {
        super(idPersona, nombrePersona, direccion, telefono, estado);
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rolModel = rolModel;
    }

    public void setPersona(PersonaModel m) {
        setDireccion(m.getDireccion());
        setEstado(m.getEstado());
        setIdPersona(m.getIdPersona());
        setNombrePersona(m.getNombrePersona());
        setTelefono(m.getTelefono());
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public RolModel getRol() {
        return rolModel;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setRol(RolModel rolModel) {
        this.rolModel = rolModel;
    }
}
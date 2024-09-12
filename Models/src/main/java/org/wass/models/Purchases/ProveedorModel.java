/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.Purchases;

/**
 *
 * @author SamuelQ
 */
public class ProveedorModel {
    private int idProveedor;
    private String nombreComercial;
    private String telefono2;
    private String correo;
    private boolean estado;
    private int idPersona;

    public ProveedorModel(String nombreComercial, String telefono2, String correo, boolean estado, int idPersona) {
        this.nombreComercial = nombreComercial;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.estado = estado;
        this.idPersona = idPersona;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
}

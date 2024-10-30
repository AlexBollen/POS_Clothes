/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.purchase;

import org.wass.models.person.PersonaModel;

/**
 *
 * @author SamuelQ
 * @author marco
 */
public class ProveedorModel extends PersonaModel {
    private int idProveedor;
    private String nombreComercial;
    private String telefono2;
    private String correo;
    private boolean estado;
    private int idPersona;

    public ProveedorModel(String nombreComercial, String telefono2, String correo, int idPersona, String nombrePersona, String direccion, String telefono) {
        super(idPersona, nombrePersona, direccion, telefono);
        this.nombreComercial = nombreComercial;
        this.telefono2 = telefono2;
        this.correo = correo;
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

    @Override
    public String toString() {
        return nombreComercial; // Esto es importante para mostrar el nombre en el ComboBox
    }
}

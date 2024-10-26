/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.sale;
import org.wass.models.person.PersonaModel;

/**
 *
 * @author SamuelQ
 * @author marco
 */
public class ClienteModel extends PersonaModel {
    private int idCliente;
    private String nit;
    private boolean estado;
    private int idPersona;


    public ClienteModel(int idPersona, String nombrePersona, String direccion, String telefono, String nit) {
        super(idPersona, nombrePersona, direccion, telefono);
        this.nit = nit;
        this.idPersona = idPersona;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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
        return nit; // Esto es importante para mostrar el nombre en el ComboBox
    }
    
}

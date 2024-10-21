/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.person;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class RolModel {
    private int IdRol;
    private String NombreRol;
    private Boolean Estado;
    
    public RolModel() {
        
    }

    public RolModel(int IdRol, String NombreRol) {
        this.IdRol = IdRol;
        this.NombreRol = NombreRol;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getNombreRol() {
        return NombreRol;
    }

    public void setNombreRol(String NombreRol) {
        this.NombreRol = NombreRol;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }
}

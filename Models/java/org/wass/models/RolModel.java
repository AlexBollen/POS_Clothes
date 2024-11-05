/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models;

import org.wass.models.Model;

/**
 *
 * @author Alex
 * @version 1.0.0
 * @since 1.0.0
 */
public class RolModel implements Model {
    
    /* Tipo de rol. */
    public static enum Tipo {
        
        // Administrador
        Administrador("Administrador"),
        // Vendedor
        Vendedor("Vendedor"),
        
        // Sin rol
        Indefinido("null");
        
        private final String rol;
        private Tipo(String rol) {
            this.rol = rol;
        }
        public String getRol() {
            return rol;
        }
        
        public static Tipo valueOf(RolModel model) {
            assert  model != null;
            if (Administrador.getRol().equals(model.getNombreRol())) {
                return Administrador;
            } else if (Vendedor.getRol().endsWith(model.getNombreRol())) {
                return Vendedor;
            }
            return Indefinido;
        }
    }
    
    private int idRol;
    private String nombreRol;
    private Boolean estado;
    
    public RolModel() { }

    public RolModel(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        
    }

    //=== ------------------------------------------------------------------ ===
    //===                           SETTERS
    //=== ------------------------------------------------------------------ ===    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    //=== ------------------------------------------------------------------ ===
    //===                           GETTERS
    //=== ------------------------------------------------------------------ ===  
    @Override
    public int getId() {
        return getIdRol();
    }    
    public int getIdRol() {
        return idRol;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public Boolean getEstado() {
        return estado;
    }
    public Tipo getTipoRol() {
        return Tipo.valueOf(this);
    }
}

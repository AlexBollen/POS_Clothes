/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.Product;
import java.util.Date;
/**
 *
 * @author SamuelQ
 */
public class StockModel {
    private int idStock;
    private int cantidadInicial;
    private int cantidadDisponible;
    private Date fechaIngreso;
    private String descripcionStock;
    private String ubicacionBodega;
    private boolean estado;
    private int idProducto;

    public StockModel(int cantidadInicial, int cantidadDisponible, Date fechaIngreso, String descripcionStock, String ubicacionBodega, boolean estado, int idProducto) {
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaIngreso = fechaIngreso;
        this.descripcionStock = descripcionStock;
        this.ubicacionBodega = ubicacionBodega;
        this.estado = estado;
        this.idProducto = idProducto;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDescripcionStock() {
        return descripcionStock;
    }

    public void setDescripcionStock(String descripcionStock) {
        this.descripcionStock = descripcionStock;
    }

    public String getUbicacionBodega() {
        return ubicacionBodega;
    }

    public void setUbicacionBodega(String ubicacionBodega) {
        this.ubicacionBodega = ubicacionBodega;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}

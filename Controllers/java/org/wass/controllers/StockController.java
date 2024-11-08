package org.wass.controllers;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.beans.ExceptionListener;
import java.util.List;

import org.wass.models.TableDataModel;
import org.wass.models.dao.StockDAO;
import org.wass.models.StockModel;
import org.wass.models.StockPosModel;

/**
 * @author SamuelQ
 */
public class StockController {
    private StockDAO stockDao;
    private ExceptionListener exceptionListener;


    public StockController() {
        this.stockDao = new StockDAO();
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    // Método para agregar un nuevo stock
    public boolean agregarStock(StockModel stock) {
        if (stock.getDescripcionStock() == null || stock.getDescripcionStock().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn del producto es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (stock.getCantidadInicial() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad inicial debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (stock.getUbicacionBodega() == null || stock.getUbicacionBodega().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La ubicacion de bodega es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = stockDao.agregarStock(stock);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Stock agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el stock", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todos los stocks
    public List<StockModel> obtenerStock() {
        return stockDao.obtenerTodosLosStocks();
    }

    // Método para obtener un stock
    public StockModel obtenerStockPorId(int idStock) {
        return stockDao.obtenerStockPorId(idStock);
    }

    // Método para obtener todos los stocks para el POS
    public List<StockPosModel> obtenerStocksPos() { return stockDao.obtenerStocksPos(); }

    // Método para eliminar un stock
    public boolean eliminarStock(int stockId) {
        boolean resultado = stockDao.eliminarStock(stockId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Stock eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el stock", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public boolean actualizarStock(StockModel stock,int IdStock) {
        if (stock.getDescripcionStock() == null || stock.getDescripcionStock().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn del producto es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (stock.getCantidadInicial() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad inicial debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (stock.getUbicacionBodega() == null || stock.getUbicacionBodega().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La ubicacion de bodega es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = stockDao.actualizarStock(stock,IdStock);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Stock actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el stock", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public String obtenerNuevaUbicacionBodega() {
        String nuevaUbicacion = stockDao.generarNuevaUbicacionBodega();
        
        if (nuevaUbicacion == null || nuevaUbicacion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error al generar una nueva ubicación para la bodega", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        //JOptionPane.showMessageDialog(null, "Nueva ubicación generada: " + nuevaUbicacion, "Información", JOptionPane.INFORMATION_MESSAGE);
        return nuevaUbicacion;
    }
}

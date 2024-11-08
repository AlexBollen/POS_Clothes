package org.wass.models.dao;

import java.beans.ExceptionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;

import org.wass.models.StockModel;
import org.wass.models.StockPosModel;

/**
 * Clase para manejar las operaciones de acceso a datos de Stock.
 *
 * @author SamuelQ
 */
public class StockDAO {
    /**
     * Método para agregar un nuevo stock a la base de datos.
     *
     * @param stock El objeto StockModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarStock(StockModel stock) {
        String sql = "INSERT INTO Stock (CantidadInicial, CantidadDisponible, FechaIngreso, DescripcionStock, UbicacionBodega, Estado, IdProducto)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stock.getCantidadInicial());
            statement.setInt(2, stock.getCantidadInicial());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setString(4, stock.getDescripcionStock());
            statement.setString(5, stock.getUbicacionBodega());
            statement.setBoolean(6, true);
            statement.setInt(7, stock.getIdProducto());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el stock: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener un stock por su ID.
     *
     * @param idStock El ID del stock a obtener
     * @return El objeto StockModel si se encuentra, null en caso contrario
     */
    public StockModel obtenerStockPorId(int idStock) {
        String sql = "SELECT * FROM Stock WHERE IdStock=?";
        StockModel stock = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idStock);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    stock = new StockModel(
                            rs.getInt("CantidadInicial"),
                            rs.getInt("CantidadDisponible"),
                            rs.getString("DescripcionStock"),
                            rs.getString("UbicacionBodega"),
                            rs.getInt("IdProducto")
                    );
                    stock.setIdStock(rs.getInt("IdStock"));
                    stock.setFechaIngreso(rs.getDate("FechaIngreso"));
                    stock.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener stock: " + e.getMessage());
        }

        return stock;
    }

    /**
     * Método para obtener todos los stocks de la base de datos.
     *
     * @return Lista de objetos StockModel
     */
    public List<StockModel> obtenerTodosLosStocks() {
        String sql = "SELECT * FROM Stock WHERE Estado=1";
        List<StockModel> stocks = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                StockModel stock = new StockModel(
                        rs.getInt("CantidadInicial"),
                        rs.getInt("CantidadDisponible"),
                        rs.getString("DescripcionStock"),
                        rs.getString("UbicacionBodega"),
                        rs.getInt("IdProducto")
                );
                stock.setIdStock(rs.getInt("IdStock"));
                stock.setFechaIngreso(rs.getDate("FechaIngreso"));
                stock.setEstado(rs.getBoolean("Estado"));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener stocks: " + e.getMessage());
        }

        return stocks;
    }

    public List<StockPosModel> obtenerStocksPos() {
        return obtenerStocksPos((excptn) -> {
            System.err.println("[ Error ] : No se puede listar los productos: " + excptn.getMessage());
        });
    }

    /**
     * Método para obtener listado de stocks con información de productos para POS
     *
     * @return Lista de objetos StockPosModel
     */
    public List<StockPosModel> obtenerStocksPos(ExceptionListener exevn) {
        String sql = "SELECT " +
                "S.IdStock, " +
                "P.NombreProducto, " +
                "TP.NombreTipoProducto, " +
                "S.CantidadDisponible, " +
                "P.PrecioVenta, " +
                "S.UbicacionBodega " +
                "FROM Stock AS S " +
                "INNER JOIN Producto AS P " +
                "ON S.IdProducto = P.IdProducto " +
                "INNER JOIN TipoProducto AS TP " +
                "ON P.IdTipoProducto = TP.IdTipoProducto " +
                "WHERE S.Estado = TRUE " +
                "HAVING S.CantidadDisponible > 0;";
        List<StockPosModel> stocks = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                StockPosModel stock = new StockPosModel(
                  rs.getInt("IdStock"),
                  rs.getString("NombreProducto"),
                  rs.getString("NombreTipoProducto"),
                  rs.getInt("CantidadDisponible"),
                  rs.getDouble("PrecioVenta"),
                  rs.getString("UbicacionBodega")
                );
                stocks.add(stock);
            }
        } catch (SQLException e) {
            exevn.exceptionThrown(e);
        }
        return stocks;
    }

    /**
     * Método para actualizar un nuevo stock a la base de datos.
     *
     * @param stock El objeto StockModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarStock(StockModel stock,int IdStock) {
        String sql = "UPDATE Stock SET CantidadInicial=?, CantidadDisponible=?, DescripcionStock=?, UbicacionBodega=?, IdProducto=? "
                + " WHERE IdStock=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stock.getCantidadInicial());
            statement.setInt(2, stock.getCantidadDisponible());
            statement.setString(3, stock.getDescripcionStock());
            statement.setString(4, stock.getUbicacionBodega());
            statement.setInt(5, stock.getIdProducto());
            statement.setInt(6, IdStock);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar stock: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para eliminar un stock por su ID.
     *
     * @param stockId El ID del stock a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarStock(int stockId) {
        String sql = "UPDATE Stock SET Estado=0 WHERE IdStock=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stockId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el stock: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método para retornar el string de la ubicación generada
     *
     * @return la nueva ubicación generada, "" si no se genera nada
     */
    public String generarNuevaUbicacionBodega() {
        String nuevaUbicacion = "";
        String sql = "SELECT UbicacionBodega FROM Stock ORDER BY UbicacionBodega DESC LIMIT 1";
        
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                String ultimaUbicacion = rs.getString("UbicacionBodega");
                nuevaUbicacion = obtenerNuevaUbicacion(ultimaUbicacion);
            } else {
                nuevaUbicacion = "A1";
            }
        } catch (SQLException e) {
            System.err.println("Error al generar nueva ubicación para stock: " + e.getMessage());
        }

        return nuevaUbicacion;
    }
    /**
     * Método para generar el string de la nueva ubicación
     *
     * @return retorna letra + numero de la nueva ubicación en bódega
     */
    public String obtenerNuevaUbicacion(String ultimaUbicacion) {
        String letra = ultimaUbicacion.replaceAll("\\d", "");
        String numeroStr = ultimaUbicacion.replaceAll("\\D", "");

        int numero = Integer.parseInt(numeroStr);
        numero += 1;

        return letra + numero;
    }
}

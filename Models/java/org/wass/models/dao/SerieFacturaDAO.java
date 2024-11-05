/*
 * POS Clothes es un sistema informático que está reservado con derechos de
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.wass.controllers.db.DataBase;
import org.wass.models.SerieFacturaModel;

/**
 * @author Alex
 */
public class SerieFacturaDAO {
    /**
     * Método para obtener todas las series de factura de la bd
     *
     * @return Lista de objetos SerieFacturaModel
     */
    public List<SerieFacturaModel> obtenerSeriesFactura() {
        String sql = "SELECT * FROM SerieFactura WHERE Estado = TRUE";
        List<SerieFacturaModel> series = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                SerieFacturaModel serie = new SerieFacturaModel(
                        rs.getString("IdSerie"),
                        rs.getDate("FechaInicio")
                );
                series.add(serie);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener series de factura: " + e.getMessage());
        }
        return series;
    }
}

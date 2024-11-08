package org.wass.models.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.wass.models.CajaModel;

import static org.wass.controllers.db.DataBase.*;

/**
 * @author SamuelQ
 */
public class CajaDAO {
    
    /**
     * Busca la caja un usuario con el rol de vendero.
     * 
     * @param idUsuario id usuario -> vendedor
     * @return modelo caja
     */
    public CajaModel obtenerCajaVendedor(int idUsuario) {
        String sql = """
                    SELECT * FROM Caja WHERE IdUsuario = ? AND EstadoCaja = TRUE
                     """;
        
        try (var db     = nDataBase().getConnection(); 
             var query  = db.prepareStatement(sql);
             var result = query.executeQuery()) {
            
            query.setInt(1, idUsuario);            
            if (result.next()) {
                CajaModel model = new CajaModel();
                model.setId(result.getInt("IdCaja"));
                
                model.setMonto(result.getFloat("Monto"));
                model.setMontoInicial(result.getFloat("MontoInicial"));
                
                model.setEstadoCaja(result.getBoolean("EstadoCaja"));
                model.setEstado(result.getBoolean("Estado "));
                
                model.setFechaApertura(result.getDate("FechaApertura"));
                model.setIdUsuario(idUsuario);
                return model;
            }
        } catch (Exception e) {
            System.err.println("Erro al obtender la caja del vendedor: "+e.getMessage());
        }
        return null;
    }
    
    /**
     * Método para agregar una nueva caja a la base de datos.
     *
     * @param caja El objeto CajaModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    @SuppressWarnings("deprecation")
    public boolean agregarCaja(CajaModel caja) {
        String sql = "INSERT INTO Caja (MontoInicial, Monto, EstadoCaja, FechaApertura, Estado, IdUsuario)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setFloat(1, caja.getMontoInicial());
            statement.setFloat(2, caja.getMonto());
            statement.setBoolean(3, caja.getEstadoCaja());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setBoolean(5, true);
            statement.setInt(6, caja.getIdUsuario());

            return statement.executeUpdate() > 0; // Devuelve true si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al agregar la caja: " + e.getMessage());
            return false; // Retorna false si hay algún error
        }
    }

    /**
     * Método para obtener todas las cajas de la base de datos.
     *
     * @return Lista de objetos CajaModel
     */
    @SuppressWarnings("deprecation")
    public List<CajaModel> obtenerCajas() {
        String sql = "SELECT * FROM Caja WHERE Estado = 1";
        List<CajaModel> cajas = new ArrayList<>();

        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                CajaModel caja = new CajaModel(
                        rs.getFloat("MontoInicial"),
                        rs.getFloat("Monto"),
                        rs.getBoolean("EstadoCaja"),
                        rs.getInt("IdUsuario")
                );
                caja.setIdCaja(rs.getInt("IdCaja"));
                caja.setFechaApertura(rs.getDate("FechaApertura"));
                caja.setEstado(rs.getBoolean("Estado"));
                cajas.add(caja);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cajas: " + e.getMessage());
        }

        return cajas;
    }

    /**
     * Método para actualizar una caja en la base de datos.
     *
     * @param caja El objeto CajaModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    @SuppressWarnings("deprecation")
    public boolean actualizarCaja(CajaModel caja,int IdCaja) {
        String sql = "UPDATE Caja SET MontoInicial=?, Monto=?, EstadoCaja=?, IdUsuario=?" +
                " WHERE IdCaja=?";

        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setFloat(1, caja.getMontoInicial());
            statement.setFloat(2, caja.getMonto());
            statement.setBoolean(3, caja.getEstadoCaja());
            statement.setInt(4, caja.getIdUsuario());
            statement.setInt(5, IdCaja);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar caja: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener una caja por su ID.
     *
     * @param id El ID de la caja a obtener
     * @return El objeto CajaModel si se encuentra, null en caso contrario
     */
    @SuppressWarnings("deprecation")
    public CajaModel obtenerCajaPorId(int id) {
        String sql = "SELECT * FROM Caja WHERE IdCaja = ?";
        CajaModel caja = null;
        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                caja = new CajaModel(
                        rs.getFloat("MontoInicial"),
                        rs.getFloat("Monto"),
                        rs.getBoolean("EstadoCaja"),
                        rs.getInt("IdUsuario")
                );
                caja.setIdCaja(rs.getInt("IdCaja"));
                caja.setFechaApertura(rs.getDate("FechaApertura"));
                caja.setEstado(rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener caja: " + e.getMessage());
        }

        return caja;
    }

    /**
     * Método para obtener caja activa por ID de usuario
     *
     * @param idUsuario ID del usuario
     * @return Objeto CajaModel si se encuentra, null en caso contrario
     */
    @SuppressWarnings("deprecation")
    public CajaModel obtenerCajaUsuario(int idUsuario) {
        String sql = "SELECT * FROM Caja WHERE " +
                "IdUsuario = ? AND " +
                "EstadoCaja = TRUE";
        CajaModel caja = null;
        try(Connection connection = nDataBase().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idUsuario);

            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    caja = new CajaModel(
                            rs.getFloat("MontoInicial"),
                            rs.getFloat("Monto"),
                            rs.getBoolean("EstadoCaja"),
                            rs.getInt("IdUsuario")
                    );
                    caja.setIdCaja(rs.getInt("IdCaja"));
                    caja.setFechaApertura(rs.getDate("FechaApertura"));
                    caja.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("El usuario no tiene una caja activa. Error: " + e.getMessage());
        }
        return caja;
    }

    /**
     * Método para eliminar una caja por su ID.
     *
     * @param cajaId El ID de la caja a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarCaja(int cajaId) {
        String sql = "UPDATE Caja SET Estado=0 WHERE IdCaja=?";

        try (Connection connection = nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cajaId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar caja: " + e.getMessage());
            return false;
        }
    }
}

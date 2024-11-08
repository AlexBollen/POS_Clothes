package org.wass.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;
import org.wass.models.TipoPagoModel;

/**
 * @author SamuelQ
 */
public class TipoPagoDAO {
    /**
     * Método para agregar un nuevo producto a la base de datos.
     *
     * @param tipoPago El objeto TipoPagoModel a agregar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean agregarTipoPago(TipoPagoModel tipoPago) {
        String sql = "INSERT INTO TipoPago (NombreTipoPago, Estado)"
                + "VALUES (?, ?)";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoPago.getNombreTipoPago());
            statement.setBoolean(2, true);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el tipo de pago: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener todos los tipos de pago de la base de datos.
     *
     * @return Lista de objetos TipoPagoModel
     */
    public List<TipoPagoModel> obtenerTiposPago() {
        String sql = "SELECT * FROM TipoPago WHERE Estado = 1";
        List<TipoPagoModel> tiposPago = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                TipoPagoModel tipoPago = new TipoPagoModel(
                        rs.getString("NombreTipoPago")
                );
                tipoPago.setIdTipoPago(rs.getInt("IdTipoPago"));
                tipoPago.setEstado(rs.getBoolean("Estado"));
                tiposPago.add(tipoPago);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de pago: " + e.getMessage());
        }

        return tiposPago;
    }

    /**
     * Método para agregar un nuevo tipo de pago a la base de datos.
     *
     * @param tipoPago El objeto TipoPagoModel a actualizar
     * @param IdTipoPago es el ID del tipo de pago a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarTipoPago(String tipoPago,int IdTipoPago) {
        String sql = "UPDATE TipoPago SET NombreTipoPago=?" +
                " WHERE IdTipoPago=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoPago);
            statement.setInt(2, IdTipoPago);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar tipo pago: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para obtener un tipo pago por su ID.
     *
     * @param id El ID del tipo pago a obtener
     * @return El objeto TipoPagoModel si se encuentra, null en caso contrario
     */
    public TipoPagoModel obtenerTipoPagoPorId(int id) {
        String sql = "SELECT * FROM TipoPago WHERE IdTipoPago = ?";
        TipoPagoModel tipoPago = null;
        
        try (Connection connection = DataBase.nDataBase().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tipoPago = new TipoPagoModel(
                            rs.getString("NombreTipoPago")
                    );
                    tipoPago.setIdTipoPago(rs.getInt("IdTipoPago"));
                    tipoPago.setEstado(rs.getBoolean("Estado"));

                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de pago por id: " + e.getMessage());
        }

        return tipoPago;
    }

    /**
     * Método para eliminar un tipo de pago por su ID.
     *
     * @param tipopagoId El ID del tipoPago a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarTipoPago(int tipopagoId) {
        String sql = "UPDATE TipoPago SET Estado=0 WHERE IdTipoPago=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, tipopagoId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo pago: " + e.getMessage());
            return false;
        }
    }
    /**
     * Método para obtener el nombre de un estado de compra por su ID.
     *
     * @param nombreTipoPago El nombre del tipo de pago a obtener
     * @return El objeto TipoPagoModel si se encuentra, null en caso contrario
     */
    public TipoPagoModel obtenerTipoPagoPorNombre(String nombreTipoPago) {
        String sql = "SELECT * FROM TipoPago WHERE NombreTipoPago = ?";
        TipoPagoModel tipopago = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombreTipoPago);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    tipopago = new TipoPagoModel(
                            rs.getString("NombreTipoProducto")
                    );
                    tipopago.setIdTipoPago(rs.getInt("IdTipoProducto"));
                    tipopago.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return tipopago;
    }
}

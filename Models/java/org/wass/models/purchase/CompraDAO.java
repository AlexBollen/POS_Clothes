package org.wass.models.purchase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.wass.controllers.db.DataBase;

public class CompraDAO {

    /**
     * Método para obtener una compra por su ID.
     *
     * @param idCompra El ID de la compra a obtener
     * @return El objeto CompraModel si se encuentra, null en caso contrario
     */
    public CompraModel obtenerCompraPorId(int idCompra) {
        String sql = "SELECT * FROM Compra WHERE IdCompra=?";
        CompraModel compra = null;

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompra);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    compra = new CompraModel(
                            rs.getString("DescripcionCompra"),
                            rs.getInt("CantidadPedida"),
                            rs.getInt("CantidadRecibida"),
                            rs.getFloat("TotalCompra"),
                            rs.getInt("IdProveedor"),
                            rs.getInt("IdTipoPago"),
                            rs.getInt("IdEstadoCompra")

                    );
                    compra.setIdCompra(rs.getInt("IdCompra")); // Seteamos el ID de la compra
                    compra.setFechaCompra(rs.getDate("FechaCompra")); // Setear la fecha de ingreso
                    compra.setEstado(rs.getBoolean("Estado")); // También seteamos el Estado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return compra; // Retorna el producto encontrado o null
    }

    /**
     * Método para obtener todas las compras de la base de datos.
     *
     * @return Lista de objetos CompraModel
     */
    public List<CompraModel> obtenerTodasLasCompras() {
        String sql = "SELECT * FROM Compra WHERE Estado=1";
        List<CompraModel> compras = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                CompraModel compra = new CompraModel(
                        rs.getString("DescripcionCompra"),
                        rs.getInt("CantidadPedida"),
                        rs.getInt("CantidadRecibida"),
                        rs.getFloat("TotalCompra"),
                        rs.getInt("IdProveedor"),
                        rs.getInt("IdTipoPago"),
                        rs.getInt("IdEstadoCompra")
                );
                compra.setIdCompra(rs.getInt("IdCompra")); // Seteamos el ID de la compra
                compra.setFechaCompra(rs.getDate("FechaCompra")); // Setear la fecha de ingreso
                compra.setEstado(rs.getBoolean("Estado")); // También seteamos el Estado
                compras.add(compra); // Agregamos la compra a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return compras; // Retorna la lista de productos
    }
    /**
     * Método para actualizar una compra en la base de datos.
     *
     * @param compra El objeto CompraModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarCompra(CompraModel compra,int IdCompra) {
        String sql = "UPDATE Compra SET DescripcionCompra=?, CantidadPedida=?, CantidadRecibida=?, TotalCompra=?, IdProveedor=?, IdTipoPago=?, IdEstadoCompra=?  "
                + " WHERE IdStock=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, compra.getDescripcionCompra());
            statement.setInt(2, compra.getCantidadPedida());
            statement.setInt(3, compra.getCantidadRecibida());
            statement.setFloat(4, compra.getTotalCompra());
            statement.setInt(5, compra.getIdProveedor());
            statement.setInt(6, compra.getIdTipoPago());
            statement.setInt(7, compra.getIdEstadoCompra());
            statement.setInt(8, IdCompra);

            return statement.executeUpdate() > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar compra: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para eliminar una compra por su ID.
     *
     * @param compraId El ID de la compra a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarCompra(int compraId) {
        String sql = "UPDATE Compra SET Estado=0 WHERE IdCompra=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, compraId);
            eliminarDetalleCompraPorCompra(compraId);
            return statement.executeUpdate() > 0; // Devuelve true si se eliminó al menos una fila
        } catch (SQLException e) {
            System.err.println("Error al eliminar compra: " + e.getMessage());
            return false; // Retorna false si hay algún error
        }
    }

    /**
     * Método para eliminar detalle de una compra.
     *
     * @param compraId ID de la compra a eliminar
     */
    private void eliminarDetalleCompraPorCompra(int compraId) throws SQLException {
        String sql = "UPDATE DetalleCompra SET Estado = 0 WHERE IdCompra = ?";
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compraId);
            statement.executeUpdate();
        }
    }
    /**
     * Método para agregar una compra.
     *
     * @param detallesCompra La lista de detalles a ingresar
     * @param compra         El objeto CompraModel a ingresar
     * @return true si se agrega compra, false si no se agrega
     */
    public boolean agregarCompra(CompraModel compra, List<DetalleCompraModel> detallesCompra) throws SQLException {
        String compraSql = "INSERT INTO Compra (DescripcionCompra, CantidadPedida, CantidadRecibida, TotalCompra, " +
                "FechaCompra, Estado, idProveedor, idTipoPago, idEstadoCompra) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String detalleCompraSql = "INSERT INTO DetalleCompra (idCompra, idProducto, CantidadProducto, Estado) " +
                "VALUES (?, ?, ?, ?)";

        // Usar try-with-resources para manejar el cierre de recursos
        try (Connection connection = DataBase.nDataBase().getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement compraStatement = connection.prepareStatement(compraSql, Statement.RETURN_GENERATED_KEYS)) {
                compraStatement.setString(1, compra.getDescripcionCompra());
                compraStatement.setInt(2, compra.getCantidadPedida());
                compraStatement.setInt(3, compra.getCantidadRecibida());
                compraStatement.setFloat(4, compra.getTotalCompra());
                compraStatement.setDate(5, Date.valueOf(LocalDate.now()));
                compraStatement.setBoolean(6, true);
                compraStatement.setInt(7, compra.getIdProveedor());
                compraStatement.setInt(8, compra.getIdTipoPago());
                compraStatement.setInt(9, compra.getIdEstadoCompra());
                compraStatement.executeUpdate();

                // Obtener el ID generado de la compra
                ResultSet generatedKeys = compraStatement.getGeneratedKeys();
                int idCompra = 0;
                if (generatedKeys.next()) {
                    idCompra = generatedKeys.getInt(1); // Obtener el idCompra generado
                }

                // Insertar los detalles de la compra
                try (PreparedStatement detalleCompraStatement = connection.prepareStatement(detalleCompraSql)) {
                    for (DetalleCompraModel detalle : detallesCompra) {
                        detalleCompraStatement.setInt(1, idCompra);  // Asignar idCompra generado
                        detalleCompraStatement.setInt(2, detalle.getIdProducto());
                        detalleCompraStatement.setInt(3, detalle.getCantidadProducto());
                        detalleCompraStatement.setBoolean(4, true);
                        detalleCompraStatement.addBatch();  // Añadir a la ejecución por lotes
                    }
                    detalleCompraStatement.executeBatch(); // Ejecutar todos los detalles juntos
                }

                // Commit de la transacción si todo sale bien
                connection.commit();
                return true;  // Retornar true porque la compra se registró exitosamente
            } catch (SQLException e) {
                // Revertir la transacción si hay algún error
                connection.rollback();
                System.err.println("Error al agregar compra: " + e.getMessage());
                return false;  // Retornar false indicando que la operación falló
            }
        }
    }
}
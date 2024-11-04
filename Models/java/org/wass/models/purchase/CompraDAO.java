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
                            rs.getFloat("TotalCompra"),
                            rs.getInt("IdProveedor"),
                            rs.getInt("IdTipoPago")

                    );
                    compra.setIdCompra(rs.getInt("IdCompra"));
                    compra.setCantidadRecibida(rs.getInt("CantidadRecibida"));
                    compra.setFechaCompra(rs.getDate("FechaCompra"));
                    compra.setIdEstadoCompra(rs.getInt("IdEstadoCompra"));
                    compra.setEstado(rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return compra;
    }

    /**
     * Método para obtener todas las compras de la base de datos.
     *
     * @return Lista de objetos CompraModel
     */
    public List<CompraModel> obtenerTodasLasCompras() {
        String sql = "SELECT * FROM Compra";
        List<CompraModel> compras = new ArrayList<>();

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                CompraModel compra = new CompraModel(
                        rs.getString("DescripcionCompra"),
                        rs.getInt("CantidadPedida"),
                        rs.getFloat("TotalCompra"),
                        rs.getInt("IdProveedor"),
                        rs.getInt("IdTipoPago")
                );
                compra.setIdCompra(rs.getInt("IdCompra"));
                compra.setCantidadRecibida(rs.getInt("CantidadRecibida"));
                compra.setFechaCompra(rs.getDate("FechaCompra"));
                compra.setIdEstadoCompra(rs.getInt("IdEstadoCompra"));
                compra.setEstado(rs.getBoolean("Estado"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return compras;
    }
    /**
     * Método para actualizar una compra en la base de datos.
     *
     * @param compra El objeto CompraModel a actualizar
     * @return true si se agrega correctamente, false en caso contrario
     */
    public boolean actualizarCompra(CompraModel compra,int idCompra) {
        String sql = "UPDATE Compra SET DescripcionCompra=?, CantidadPedida=?, CantidadRecibida=?, TotalCompra=?, IdProveedor=?, IdTipoPago=?, IdEstadoCompra=?  "
                + " WHERE IdCompra=?";

        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, compra.getDescripcionCompra());
            statement.setInt(2, compra.getCantidadPedida());
            statement.setInt(3, compra.getCantidadRecibida());
            statement.setFloat(4, compra.getTotalCompra());
            statement.setInt(5, compra.getIdProveedor());
            statement.setInt(6, compra.getIdTipoPago());
            statement.setInt(7, compra.getIdEstadoCompra());
            statement.setInt(8, idCompra);

            return statement.executeUpdate() > 0;

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
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar compra: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para eliminar detalle de una compra.
     *
     * @param compraId ID de la compra a eliminar
     */
    private void eliminarDetalleCompraPorCompra(int compraId) throws SQLException {
        String sql = "UPDATE Compra SET Estado = 0, IdEstadoCompra = 4 WHERE IdCompra = ?";
        try (Connection connection = DataBase.nDataBase().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compraId);
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al eliminar la compra: " + e.getMessage());
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
                compraStatement.setInt(9, 1);
                compraStatement.executeUpdate();

                // Obtener el ID generado de la compra
                ResultSet generatedKeys = compraStatement.getGeneratedKeys();
                int idCompra = 0;
                if (generatedKeys.next()) {
                    idCompra = generatedKeys.getInt(1); // Obtener el idCompra que se acaba de crear
                }

                // Insertar los detalles de la compra
                try (PreparedStatement detalleCompraStatement = connection.prepareStatement(detalleCompraSql)) {
                    for (DetalleCompraModel detalle : detallesCompra) {
                        detalleCompraStatement.setInt(1, idCompra);  // Asignar idCompra que se acaba de crear
                        detalleCompraStatement.setInt(2, detalle.getIdProducto());
                        detalleCompraStatement.setInt(3, detalle.getCantidadProducto());
                        detalleCompraStatement.setBoolean(4, true);
                        detalleCompraStatement.addBatch();
                    }
                    detalleCompraStatement.executeBatch(); // Ejecutar todos los detalles juntos
                }

                connection.commit();
                return true;
            } catch (SQLException e) {
                // Revertir la transacción si hay algún error
                connection.rollback();
                System.err.println("Error al agregar compra: " + e.getMessage());
                return false;
            }
        }
    }
    
    public String terminarCompra(CompraModel compra, String detalleVenta, String ubicacionNueva) {
        String storedProcedure = "{call spCompra (?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DataBase.nDataBase().getConnection();
             CallableStatement statement = connection.prepareCall(storedProcedure)) {
            statement.setInt(1, compra.getIdCompra());
            statement.setString(2, compra.getDescripcionCompra());
            statement.setInt(3, compra.getCantidadPedida());
            statement.setInt(4, compra.getCantidadRecibida());
            statement.setString(5, ubicacionNueva);
            statement.setString(6, detalleVenta);

            statement.registerOutParameter(7, Types.VARCHAR);
            statement.registerOutParameter(8, Types.INTEGER);

            statement.execute();

            String mensaje = statement.getString(7);
            int estado = statement.getInt(8);

            return Integer.toString(estado).concat(" - " + mensaje);
        } catch (SQLException e) {
            System.err.println("Error al actualizar la compra: " + e.getMessage());
            return "0 - Ha sucedido un error: " + e.getMessage();
        }
    }
}

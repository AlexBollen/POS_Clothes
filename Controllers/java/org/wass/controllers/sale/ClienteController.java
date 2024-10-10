package org.wass.controllers.sale;

/**
 *
 * @author SamuelQ
 */
import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.sale.ClienteDAO;
import org.wass.models.sale.ClienteModel;

public class ClienteController {
    private ClienteDAO clienteDao;

    // Constructor para inicializar productoDao
    public ClienteController(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    // Método para agregar un nuevo cliente
    public boolean agregarCliente(ClienteModel cliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("");
        }
        if (cliente.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean resultado = clienteDao.agregarCliente(cliente);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar clienter", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todos los clientes
    public List<ClienteModel> obtenerClientes() {
        return clienteDao.obtenerClientes();
    }

    // Método para obtener un cliente
    public ClienteModel obtenerClientePorId(int idCliente) {
        return clienteDao.obtenerClientePorId(idCliente);
    }

    // Método para eliminar un cliente
    public boolean eliminarCliente(int clienteId) {
        boolean resultado = clienteDao.eliminarCliente(clienteId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(ClienteModel cliente,int IdCliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("");
        }
        if (cliente.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = clienteDao.actualizarCliente(cliente,IdCliente);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar clienter", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    //Método para obtener un cliente por su nit
    public ClienteModel obtenerClientePorNit(String nit) {
        return clienteDao.obtenerClientePorNit(nit);
    }
}

package org.wass.controllers;

import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.dao.ClienteDAO;
import org.wass.models.ClienteModel;

/**
 * @author SamuelQ
 * @author marco
 */
public class ClienteController {
    private ClienteDAO clienteDao;

    // Constructor para inicializar clienteDAO    
    public ClienteController() {
        this(new ClienteDAO());
    }
    @Deprecated
    public ClienteController(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    // Método para agregar un nuevo cliente
    public boolean agregarCliente(ClienteModel cliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("CF");
        }
        if (cliente.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean resultado = clienteDao.agregarCliente(cliente);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para agragar una nueva persona y un cliente
    public boolean agregarClientePersona(ClienteModel cliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("CF");
        }
        if (cliente.getNombrePersona() == null || cliente.getNombrePersona().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe  ingresar un nombre", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            cliente.setDireccion("");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().isEmpty()) {
            cliente.setTelefono("");
        }

        boolean resultado = clienteDao.agregarClientePersona(cliente);
       
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

        return resultado;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(ClienteModel cliente,int idCliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("");
        }
        if (cliente.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = clienteDao.actualizarCliente(cliente, idCliente);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para actualizar un cliente y una peresona
    public boolean actualizarClientePersona(ClienteModel cliente,int idCliente) {
        if (cliente.getNit() == null || cliente.getNit().isEmpty()) {
            cliente.setNit("CF");
        }
        if (cliente.getNombrePersona() == null || cliente.getNombrePersona().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe  ingresar un nombre", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            cliente.setDireccion("");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().isEmpty()) {
            cliente.setTelefono("");
        }


        boolean resultado = clienteDao.actualizarClientePersona(cliente, idCliente);
     
        return resultado;
    }
    //Método para obtener un cliente por su nit
    public ClienteModel obtenerClientePorNit(String nit) {
        return clienteDao.obtenerClientePorNit(nit);
    }
}

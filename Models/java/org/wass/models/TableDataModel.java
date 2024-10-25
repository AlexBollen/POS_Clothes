package org.wass.models;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * Clase generica encargado de gestionar el modelo de una tabla swing, dicho
 * objeto gestiona los modelos físico de la DB.
 * 
 * @author wil
 * @param <E> tipo modelo
 */
public class TableDataModel<E extends Model> extends DefaultTableModel {

    /**
     * mapa encargado de cachear los modelos mediante un id (normalemnte de la tabla
     * física al que pertenecen) donde se lleva un control de ellos.
     */
    private Map<Integer, E> mapModel;

    /**
     * Genera un nuevo modelo swing-
     * @param columnas columnas de la tabla (nombres)
     */
    public TableDataModel(String... columnas) {
        super(columnas, 0);
        this.mapModel = new HashMap<>();
    }

    /**
     * Devuelve un modelo medinate la posicón de la fila.
     * 
     * @param row numero de fial
     * @return el modelo
     */
    public E getValueAt(int row) {
        int idUsuario = (int) getValueAt(row, 0);
        return mapModel.get(idUsuario);
    }

    /**
     * Agrega una nueva fila a la tabla modelo.
     * 
     * @param model modelo físico
     * @param display gestor de información
     */
    public void addRow(E model, DisplayTableModel<E> display) {
        addRow(display.display(model));
        mapModel.put(model.getId(), model);
    }

    /*
     * Deshabilita la edición de todas las celdas,
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

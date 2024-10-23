package org.wass.models;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * @author wil
 * @param <E>
 */
public class TableDataModel<E extends Model> extends DefaultTableModel {

    private Map<Integer, E> mapModel;

    public TableDataModel(String... columnas) {
        super(columnas, 0);
        this.mapModel = new HashMap<>();
    }

    public E getValueAt(int row) {
        int idUsuario = (int) getValueAt(row, 0);
        return mapModel.get(idUsuario);
    }

    public void addRow(E model, DisplayTableModel<E> display) {
        addRow(display.display(model));
        mapModel.put(model.getId(), model);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

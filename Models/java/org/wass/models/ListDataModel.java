package org.wass.models;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * Una abstraccion de un modelo que utiliza los componentes {@link javax.swing.JComboBox}
 * para mostrar su contenido.
 * 
 * @author wil
 * @param <E> tipo modelo
 */
public class ListDataModel<E extends Model> extends DefaultComboBoxModel<Object> {
    
    /**
     * Clase interna encargado de gestionar un registro para devolver el modelo
     * seleccioando por dicho compoentes.
     * @param <T> tipo modelo
     */
    static class EntryListData<T extends Model> {
        
        /** Identificado (obtenida de la tabla). */
        final int id;
        /** Modelo. */
        final T value;        
        /** Gestor de información.*/
        final DisplayListModel<T> display;
        
        // Constructor
        public EntryListData(int id, T value, DisplayListModel<T> display) {
            this.id = id;
            this.value = value;
            this.display = display;
        }

        // Getter
        public int getId() { return id; }
        public T getValue() { return value; }

        // info a mostrar
        @Override
        public String toString() {
            return String.valueOf(display.display(value));
        }
    }
    
    public ListDataModel() {
    }
    
    /**
     * Devuelve el modelo seleccioando.
     * @return modelo
     */
    @SuppressWarnings("unchecked")
    public E getSelectedModel() {
        return (E) ((EntryListData) getSelectedItem()).value;
    }
    
    /**
     * Establece un modelo para su seleccion predetermianda.
     * @param item modelo
     */
    public void setDefaultSelectedItem(E item) {
        setDefaultSelectedItem(item.getId());
    }
    
    /**
     * Establece un modelo para su seleccion predetermianda.
     * @param id id del modelo
     */
    public void setDefaultSelectedItem(int id) {
        for (int i = 0; i < getSize(); i++) {
            EntryListData entry = (EntryListData) getElementAt(i);
            if (entry.getId() == id) {
                setSelectedItem(entry);
                break;
            }
        }
    }
    
    /*
     * Agrega una nuevo elemento al modelo del componente gráfico.
     */
    public ListDataModel<E> addAll(List<E> data, DisplayListModel<E> display) {
        for (final E element : data) {
            addElement(new EntryListData<>(element.getId(), element, display));
        }
        return this;
    }
}

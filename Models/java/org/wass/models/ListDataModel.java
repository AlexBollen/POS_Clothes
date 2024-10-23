package org.wass.models;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 * @author wil
 * @param <E>
 */
public class ListDataModel<E extends Model> extends DefaultComboBoxModel<Object> {
    
    static class EntryListData<T extends Model> {
        final int id;
        final T value;
        final DisplayListModel<T> display;

        public EntryListData(int id, T value, DisplayListModel<T> display) {
            this.id = id;
            this.value = value;
            this.display = display;
        }

        public int getId() {
            return id;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(display.display(value));
        }
    }
    
    public ListDataModel() {
    }
    
    @SuppressWarnings("unchecked")
    public E getSelectedModel() {
        return (E) ((EntryListData) getSelectedItem()).value;
    }
    
    public void setDefaultSelectedItem(E item) {
        setDefaultSelectedItem(item.getId());
    }
    public void setDefaultSelectedItem(int id) {
        for (int i = 0; i < getSize(); i++) {
            EntryListData entry = (EntryListData) getElementAt(i);
            if (entry.getId() == id) {
                setSelectedItem(entry);
                break;
            }
        }
    }
        
    public ListDataModel<E> addAll(List<E> data, DisplayListModel<E> display) {
        for (final E element : data) {
            addElement(new EntryListData<>(element.getId(), element, display));
        }
        return this;
    }
}

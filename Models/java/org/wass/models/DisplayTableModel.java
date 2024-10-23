package org.wass.models;

/**
 * @author wil
 * @param <E>
 */
public interface DisplayTableModel<E extends Model> {
    
    Object[] display(E model);
}

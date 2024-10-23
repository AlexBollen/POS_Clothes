package org.wass.models;

/**
 * @author wil
 * @param <E>
 */
public interface DisplayListModel<E extends Model> {
    
    Object display(E model);
}

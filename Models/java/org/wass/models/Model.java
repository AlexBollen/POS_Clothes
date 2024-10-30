package org.wass.models;

/**
 * Interfaz encargado de gestionar la representación de los modelos físico de una
 * tabla relacional.
 * <p>
 * Cualquier objeto que implemente esta interfaz puede acceder a las abstracciones
 * de los compoentes {@link ListDataModel} y {@link TableDataModel}, para automatizar
 * la información a mostrar.
 * 
 * @author wil
 */
public interface Model {
    
    /**
     * Indetificador unico del modelo físico.
     * @return int
     */
    public int getId();
}

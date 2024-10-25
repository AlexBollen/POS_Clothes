package org.wass.models;

/**
 * Interfaz encargado de gestionar la información a mostra de un modelo en un
 * ComboBox swing ({@link ListDataModel}).
 * <p>
 * Ejemplo:
 * <pre><code>
 * ListDataModel&#60;MyModel&#62; model = new ListDataModel&#60;&#62;();
 * model.addAll(myListData, (m) -> {
 *      return m.getCampoMostrarLista(); // -> El campo a visualizar.
 * });
 * </code></pre>
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 * @param <E> tipo de modelo
 */
public interface DisplayListModel<E extends Model> {
    
    /**
     * Devuelve la información a mostrar en la tabla.
     * 
     * @param model modelo a gestionar
     * @return informacion del modelo (solamente de un campo)
     */
    Object display(E model);
}

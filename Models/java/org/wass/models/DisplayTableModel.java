package org.wass.models;

/**
 * Interfaz encargado de gestionar la información a mostra en una tabla de un
 * Jtable swing ({@link TableDataModel}).
 * <p>
 * Ejemplo:
 * <pre><code>
 * TableDataModel&#60;MyModel&#62; model = new TableDataModel&#60;&#62;();
 * model.addAll(myListData, (m) -> {
 *      return new Object[] {
 *          m.getCampo1(),
 *          m.getCampo2()
 *      }
 * });
 * </code></pre>
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 * @param <E> tipo de modelo
 */
public interface DisplayTableModel<E extends Model> {
    
    /**
     * Fila de información a mostra en la tabla.
     * @param model modelo a gestionar
     * @return fila de información
     */
    Object[] display(E model);
}

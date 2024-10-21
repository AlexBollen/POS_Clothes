package org.wass.views.component.renderer;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author wil
 * @param <E>
 */
public class GestionTableModel<E> extends DefaultTableModel {
    
    public GestionTableModel(List<String> titulos, List<List<E>> data) {
        super(data.toArray(Object[][]::new), titulos.toArray(String[]::new));
    }
}

package org.wass.views.component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.wass.views.listeners.SearchListener;

public class Search extends JPanel {
    
    private static class SearchFilter extends RowFilter<TableModel, Integer> {

        private Matcher matcher;
        private int limite   = 0;
        private int contador = 0;
        
        private int[] columnas;
        private String value;
        
        public SearchFilter(String value, int ...columnas) {
            assert value != null;
            this.value = value;
            this.columnas = columnas;
            
            Pattern regex = Pattern.compile(value.toLowerCase());
            this.matcher = regex.matcher("");
        }

        public SearchFilter setLimite(int limite) {
            this.limite = limite;
            return this;
        }

        @Override
        public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
            if (columnas == null || value.trim().isEmpty()) {
                return true;
            }
            
            if (limite > 0 && contador >= limite) {
                return false;
            }
            
            for (int c : columnas) {
                matcher.reset(entry.getStringValue(c).toLowerCase());
                if (!matcher.find()) {
                    return false;
                }
            }
            contador++;
            return true;
        }
    }
        
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private List<RowSorter.SortKey>sortKeys;
    private SortOrder order = SortOrder.UNSORTED;
    private SearchListener searchListener;
    
    public Search() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Search_black.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(20, 16));
        jButton1.setMinimumSize(new java.awt.Dimension(20, 16));
        jButton1.setPreferredSize(new java.awt.Dimension(20, 16));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, java.awt.BorderLayout.LINE_END);

        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 1));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        add(jTextField1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        filtrar();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        filtrar();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void setOrder(SortOrder order) {
        this.order = order;
    }

    public void filtrar() {
        filtrar0(jTextField1.getText().trim());
    }
    
    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public void setTable(JTable table) {
        assert table != null;        
        this.table    = table;
        this.sortKeys = new ArrayList<>();
        this.sorter   = new TableRowSorter<>(table.getModel());
        
        this.table.setAutoCreateRowSorter(true);
        this.table.setRowSorter(sorter);
    }
    
    private void filtrar0(String value) {
        if (check()) {
            TableModel model = table.getModel();
            if (model != sorter.getModel()) {
                sorter.setModel(model);
            }
            sortKeys.clear();
            int[] columnas = searchListener.search();
            for (int i = 0; i < columnas.length; i++) {
                sortKeys.add(new RowSorter.SortKey(i, order));
            }
            
            sorter.setSortKeys(sortKeys);
            sorter.setRowFilter(new SearchFilter(value, columnas)
                                            .setLimite(searchListener.limit()));
        }
    }
    
    private boolean check() {
        return table != null && searchListener != null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

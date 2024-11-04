package org.wass.views.component.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import org.wass.models.person.RolModel;
import org.wass.views.component.renderer.BorderRenderer;

/**
 *
 * @author marco
 */
public class Menu  extends JComponent{
    
    public static final Color BACKGROUND        = new Color(30, 31, 34);
    public static final Color SELECTED_COLOR    = new Color(46, 67, 110);
    
    public static final Color DEFAULT_FOREGROUND  = new Color(187,187,187);
    public static final Color SELECTED_FOREGROUND = new Color(244, 244, 244);
    
    private final Map<MenuItem, Map<Integer, MenuItem>> cache = new HashMap<>();
    private MigLayout layout;
    private MenuItemGroup group;
    
    private MenuEvent event;
    private String [][] menuItems;
    
    private RolModel.Tipo tipo;
    
    public Menu(RolModel.Tipo tipo){
        this.tipo = tipo;
        this.init();
    }

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }
    private void init(){
        switch (tipo) {
            case Administrador -> {
                menuItems = new String [][]{
                    {"Dashboard"},
                    {"Ventas", "Nueva Venta", "Historial"},
                    {"Inventario"},
                    {"Compras"},
                    {"Beneficiarios", "Clientes", "Proveedores"},
                    {"Cajas"},
                    {"Reportes"}
                };
            }
            case Vendedor -> {
                menuItems = new String [][]{
                    {"Dashboard"},
                    {"Ventas", "Nueva Venta", "Historial"}
                };
            }
            case Indefinido -> { }
            default -> {}
        }
        
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        group = new MenuItemGroup();
        group.addMenuItemSelectedListener((MenuItem item, boolean b) -> {
            Border border = item.getBorder();
            Insets insets = null;
            
            if (border instanceof EmptyBorder emptyBorder) {
                insets = emptyBorder.getBorderInsets();
            }
            
            if (b) {
                item.setBackground(SELECTED_COLOR);
                item.setForeground(SELECTED_FOREGROUND);
                
                if (insets != null) {
                    insets.right = 4;
                    item.setBorder(new BorderRenderer(insets)
                                            .addBorderLineColor(BorderRenderer.BorderLine.Right, new Color( 53, 116, 240)));
                }
            } else {
                item.setBackground(BACKGROUND);
                item.setForeground(DEFAULT_FOREGROUND);
                item.setBorder(new BorderRenderer(9, 10, 9, 10));
                
                if (insets != null) {
                    item.setBorder(new EmptyBorder(insets));
                }
            }
        });
        
        setLayout(layout);
        setOpaque(true);
        
        //  Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }
        
        setBackground(BACKGROUND);
    }
    
     private Icon getIcon(int index) {         
        URL url = getClass().getResource("/Iconos/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }        
    }

    
    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        
        if (length > 1) {
            item.setContentAreaFilled(false);
            cache.put(item, new HashMap<>());
        } else {
            group.add(item);
        }
        
        if (index == 0) {
            item.setSelecteItem(true);
        }
        
        item.addActionListener((ActionEvent ae) -> {            
            if (length > 1) {
                if (!item.isSelected()) {
                    item.setSelected(true);
                    addSubMenu(item, index, length, getComponentZOrder(item));
                } else {
                    //  Hide menu
                    hideMenu(item, index);
                    item.setSelected(false);
                }
            } else {
                if (event != null) {
                    event.selected(index, 0);
                }
                item.setSelecteItem(true);
            }
        });
        add(item);
        revalidate();
        repaint();
    }
    
    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setOpaque(false); 
       
        Map<Integer, MenuItem> cachMenu = cache.get(item);
        
        
        for (int i = 1; i < length; i++) {
            if (cachMenu != null) {
                MenuItem subItem = cachMenu.get(i);
                if (subItem != null) {
                    panel.add(subItem);
                    continue;
                }
            }
            
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener((ActionEvent ae) -> {
                if (event != null) {
                    event.selected(index, subItem.getIndex());
                }
                subItem.setSelecteItem(true);
            });
            subItem.initSubMenu(i, length);
            panel.add(subItem);

            cachMenu.put(i, subItem);
            group.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }
    
    
    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }
    
     @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(BACKGROUND);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }
}

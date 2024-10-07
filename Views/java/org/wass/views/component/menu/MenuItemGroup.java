package org.wass.views.component.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MenuItemGroup {
    
    private final List<MenuItem> buttons = new ArrayList<>();    
    private final List<MenuItemSelectedListener> listeners;
    
    MenuItem selection = null;
    
    public MenuItemGroup() {
        this.listeners =  new ArrayList<>();
    }
    
    public void add(MenuItem b) {
        if(b == null) {
            return;
        }
        buttons.add(b);

        if (b.isSelected()) {
            if (selection == null) {
                selection = b;
            } else {
                fireMenuItemSelectedListener(b, false);
            }
        }

        b.setGroup(this);
    }
    
    public void remove(MenuItem b) {
        if(b == null) {
            return;
        }
        buttons.remove(b);
        if(b.getModel() == selection) {
            selection = null;
        }
        b.setGroup(null);
    }
    
    public void clearSelection() {
        if (selection != null) {
            MenuItem oldSelection = selection;
            selection = null;
            fireMenuItemSelectedListener(oldSelection, false);
        }
    }    
    
    public Iterator<MenuItem> getElements() {
        return buttons.iterator();
    }
    
    public MenuItem getSelection() {
        return selection;
    }
    
    public void setSelected(MenuItem m, boolean b) {
        if (b && m != null && m != selection) {
            MenuItem oldSelection = selection;
            selection = m;
            if (oldSelection != null) {
                fireMenuItemSelectedListener(oldSelection, false);
            }
            fireMenuItemSelectedListener(m, true);
        }
    }
    
    public boolean isSelected(MenuItem m) {
        return (m == selection);
    }
    
    public int getButtonCount() {
        if (buttons == null) {
            return 0;
        } else {
            return buttons.size();
        }
    }
    
    public void addMenuItemSelectedListener(MenuItemSelectedListener misl) {
        this.listeners.add(misl);
    }
    
    public boolean removeMenuItemSelectedListener(MenuItemSelectedListener misl) {
        return this.listeners.remove(misl);
    }
    
    private void fireMenuItemSelectedListener(MenuItem item, boolean b) {
        for (final MenuItemSelectedListener misl : this.listeners) {
            if (misl != null) {
                misl.onSelected(item, b);
            }
        }
    }
}

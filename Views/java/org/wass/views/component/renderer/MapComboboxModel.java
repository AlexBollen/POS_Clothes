package org.wass.views.component.renderer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class MapComboboxModel<K, V> extends HashMap<K, V> implements ListModel<K>, MutableComboBoxModel<K> {

    protected EventListenerList listenerList = new EventListenerList();
    private K selected;

    @Override
    public int getSize() {
        return size();
    }

    public V getSelected() {
        return get(selected);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        int startIndex = getSize();
        super.putAll(m);
        fireIntervalAdded(this, startIndex, getSize() - 1);
    }
    
    @Override
    public V put(K key, V value) {
        V v = super.put(key, value);
        fireIntervalAdded(this, size()-1, size()-1);
        if (size() == 1 && selected == null && key != null) {
            setSelectedItem(key);
        }
        return v;
    }

    @Override
    public K getElementAt(int i) {
        Iterator<K> keys = keySet().iterator();
        int j = 0;
        
        while (keys.hasNext()) {
            K next = keys.next();
            if (j == i) {
                return next;
            }
            j++;
        }
        return null;
    }

    @Override
    public void clear() {
        if (! isEmpty()) {
            int firstIndex = 0;
            int lastIndex = size() - 1;
            super.clear();
            selected = null;
            fireIntervalRemoved(this, firstIndex, lastIndex);
        } else {
            selected = null;
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listenerList.add(ListDataListener.class, l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listenerList.remove(ListDataListener.class, l);
    }
    protected void fireContentsChanged(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((ListDataListener)listeners[i+1]).contentsChanged(e);
            }
        }
    }
    
    
    protected void fireIntervalAdded(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.INTERVAL_ADDED, index0, index1);
                }
                ((ListDataListener)listeners[i+1]).intervalAdded(e);
            }
        }
    }
    
    protected void fireIntervalRemoved(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.INTERVAL_REMOVED, index0, index1);
                }
                ((ListDataListener)listeners[i+1]).intervalRemoved(e);
            }
        }
    }
    

    @Override
    public void addElement(K e) {
        put(e, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeElement(Object o) {
        remove((K)o);
    }

    @Override
    public void insertElementAt(K e, int i) {
        put(e, null);
    }

    @Override
    public void removeElementAt(int i) {
        if (getElementAt(i) == selected) {
            if ( i == 0 ) {
                setSelectedItem(getSize() == 1 ? null : getElementAt(i + 1));
            } else {
                setSelectedItem(getElementAt(i - 1));
            }
        }
        
        remove(getElementAt(i));
        fireIntervalRemoved(this, i, i);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setSelectedItem(Object o) {
        if ((selected != null && !selected.equals(o)) ||
            selected == null && o != null) {
            selected =  (K) o;
            fireContentsChanged(this, -1, -1);
        }
    }

    @Override
    public K getSelectedItem() {
        return selected;
    }
}

/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views.component.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public class BorderRenderer extends EmptyBorder {
    
    public static enum BorderLine {
        Top,
        Left,
        Bottom,
        Right;
    }
    
    private Map<BorderLine, Color> mapBorderLine;
    
    public BorderRenderer(Insets insets) {
        this(insets.top, insets.left, insets.bottom, insets.right);
    }
    
    public BorderRenderer(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
        this.mapBorderLine = new HashMap<>();
    }
    
    public void setBorderLineColor(BorderLine line, Color color) {
        this.mapBorderLine.put(line, color);
    }
    
    public BorderRenderer addBorderLineColor(BorderLine line, Color color) {
        setBorderLineColor(line, color);
        return this;
    }
    
    public Color getBorderLineColor(BorderLine line) {
        return this.mapBorderLine.get(line);
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets insets = getBorderInsets(c);
        Color oldColor = g.getColor();
        g.translate(x, y);

        Color color = getBorderLineColor(BorderLine.Top);
        if (color != null) {
            g.setColor(color);
            g.fillRect(0, 0, width - insets.right, insets.top);
        }
        
        color = getBorderLineColor(BorderLine.Left);
        if (color != null) {
            g.setColor(color);
            g.fillRect(0, insets.top, insets.left, height - insets.top);
        }
        
        color = getBorderLineColor(BorderLine.Bottom);
        if (color != null) {
            g.setColor(color);
            g.fillRect(insets.left, height - insets.bottom, width - insets.left, insets.bottom);
        }
        
        color = getBorderLineColor(BorderLine.Right);
        if (color != null) {
            g.setColor(color);
            g.fillRect(width - insets.right, 0, insets.right, height + insets.bottom);
        }
        
        g.translate(-x, -y);
        g.setColor(oldColor);
    }
}

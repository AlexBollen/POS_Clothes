/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

/**
 * Clase abstracta encargado de gestiona un sistema de botones donde se puede tener
 * la opcion de aceptar y/o cancelaer (solo la lógica).
 * <p>
 * Dicho componentes (botones) se tienen que implementar y mediante los oyenes
 * de eventos activarlo.
 * 
 * @author wil
 */
public abstract class AbstractOkCancelDialog extends JDialog {
    
    /**
     * Un código de estado de devolución: se devuelve si se ha presionado el 
     * botón Cancelar.
     */
    public static final int RET_CANCEL = 0;
    /**
     * Un código de estado de devolución: se devuelve si se ha presionado el 
     * botón OK.
     */
    public static final int RET_OK = 1;
    
    /** El estado actual. */
    private int status;

    /*
    Constructores.
    */
    public AbstractOkCancelDialog(Frame owner, boolean modal) {
        super(owner, modal);
        simpleInitDialog();
    }
    public AbstractOkCancelDialog(Dialog owner, boolean modal) {
        super(owner, modal);
        simpleInitDialog();
    }
    
    /**
     * Inicializa los componetes para el cuadro de diálogo.
     */
    private void simpleInitDialog() {
        // Cerrar el cuadro de diálogo cuando se presiona Esc
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        
        try {
            setIconImages(new ArrayList<>() {
                {
                    this.add(ImageIO.read(AbstractFrame.class.getResource("/Images/favicon.png")));
                }
            });
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }        
    }
    protected void setDefaultButton(JButton button) {
        getRootPane().setDefaultButton(button);
    }
    
    /**
     * Esta esta venta emergente.
     * @param retStatus estado.
     */
    public void doClose(int retStatus) {
        status = retStatus;
        setVisible(false);
        dispose();
    }
    
    /**
     * el estado de retorno de este cuadro de diálogo: uno de {@code RET_OK} o 
     * {@code RET_CANCEL}
     * @return int
     */
    public int getReturnStatus() {
        return status;
    }
}

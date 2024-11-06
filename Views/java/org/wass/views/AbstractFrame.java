/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.views;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFrame extends JFrame {

    protected Cache cache;
    public AbstractFrame() {
        simpleInitFrame();
    }
    
    private void simpleInitFrame() {
        cache = Sistema.getCache();
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
    
    protected Cache.Conf getCacheWin() {
        return cache.getConfiguracion(Cache.CACHE_APLICACION);
    }
    
    protected Cache.Conf getCacheDB() {
        return cache.getConfiguracion(Cache.CACHE_BASE_DATOS);
    }
}

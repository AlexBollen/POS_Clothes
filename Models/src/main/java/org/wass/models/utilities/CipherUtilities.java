/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.utilities;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies;

import java.nio.charset.StandardCharsets;

/**
 * Clase utilidades <code>CipherUtilities</code> encargado de proprocionar 
 * métodos de cifrado seguro.
 * <p>
 * Para el cifrado de contraseñas, se utiliza la biblioteta BCrypt (https://github.com/patrickfav/bcrypt) 
 * donde <code>CipherUtilities</code> ofrece método que facilita el uso de ello, se 
 * puede implementarlo de la siguiente manera:<pre><code>
 * String shortPass = "myPassword",
 *        longPass = "LongPassword......";
 * 
 * ----------------------------------- CIFRAR ----------------------------------
 * byte[] shortCrypt = nBCryptGenerateNewRandomHash(shortPass),
 *        longCrypt  = nBCryptGenerateNewRandomHash(longPass);
 *      
 * --------------------------------- VERIFICAR ---------------------------------
 * if (nBCryptVerifyerRandomHash(shortCrypt, shortPass)) {
 *  ...
 * }
 * if (nBCryptVerifyerRandomHash(longCrypt, longPass)) {
 *  ...
 * }
 * </code></pre>
 * 
 * @author wil
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CipherUtilities {
    
    /**
     * Tamaño máximo que soporta el algoritmo de cifrado Blowfish, dicho tamaño 
     * es utilizado por <b>BCrypt</b> para generar hashes.
     */
    public static final int MAX_BLOWFISH   = 72;
    
    /**
     * El costo del algoritmo a utilizar, entre más grande sea el costo más 
     * complicado será el cifrado lo que implica más tiempo de romperlo.
     */
    public static final int COST_BLOWFISH = 6;
    
    /**
     * Método encargado de generar un hash aleatorio mediante texto plano, dicho 
     * hash utiliza la versión {@code BCrypt.Version.VERSION_2Y} compatible con 
     * la implementación de PHP.
     * 
     * @param value Los datos a encriptar (String)
     * @return datos cifrados 
     */
    public static byte[] nBCryptGenerateNewRandomHash(String value) {
        return nBCryptGenerateNewRandomHash(value.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Método encargado de generar un hash aleatorio mediante texto plano, dicho 
     * hash utiliza la versión {@code BCrypt.Version.VERSION_2Y} compatible con 
     * la implementación de PHP.
     * 
     * @param bits Los datos a encriptar
     * @return datos cifrados 
     */
    public static byte[] nBCryptGenerateNewRandomHash(byte[] bits) {
        if (bits == null) {
            throw new IllegalArgumentException("Valor no válido: null");
        }        
        if (bits.length > MAX_BLOWFISH) {
            return BCrypt.with(BCrypt.Version.VERSION_2Y, LongPasswordStrategies.hashSha512(BCrypt.Version.VERSION_2Y)).hash(COST_BLOWFISH, bits);
        } else {
            return BCrypt.with(BCrypt.Version.VERSION_2Y).hash(COST_BLOWFISH, bits);
        }
    }
    
    /* Otra forma de verificar valores BCrypt */
    public static boolean nBCryptVerifyerRandomHash(String encrypted, String simple) {
        return nBCryptVerifyerRandomHash(encrypted.getBytes(StandardCharsets.UTF_8), simple.getBytes(StandardCharsets.UTF_8));
    }    
    /* Otra forma de verificar valores BCrypt */
    public static boolean nBCryptVerifyerRandomHash(byte[] bitsEncrypted, String simple) { 
        return nBCryptVerifyerRandomHash(bitsEncrypted, simple.getBytes(StandardCharsets.UTF_8)); 
    }
    
    /**
     * Un cifrado seguro no tendría que ser descifrado de manera fácil, este 
     * método simplemente verifica si un hash hecho es igual a un segundo valor, 
     * de ser así devolverá <code>true</code>, de lo contrario <code>false</code>.
     * 
     * @param bitsEncrypted El hash recurpadro de la DB (u otro lado)
     * @param simpleBits El valor a comprobar  
     * @return boolean
     */
    public static boolean nBCryptVerifyerRandomHash(byte[] bitsEncrypted, byte[] simpleBits) {
        if (bitsEncrypted == null || simpleBits == null) {
            throw new IllegalArgumentException("Valor no válido: null | null");
        }
        
        BCrypt.Result result;
        if (simpleBits.length > MAX_BLOWFISH) {
            result = BCrypt.verifyer(BCrypt.Version.VERSION_2Y, LongPasswordStrategies.hashSha512(BCrypt.Version.VERSION_2Y)).verify(simpleBits, bitsEncrypted);
        } else {
            result = BCrypt.verifyer(BCrypt.Version.VERSION_2Y).verify(simpleBits, bitsEncrypted);            
        }
        return result.verified;
    }
}

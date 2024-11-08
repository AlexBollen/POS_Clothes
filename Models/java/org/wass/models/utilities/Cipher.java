/*
 * POS Clothes es un sistema informático que está reservado con derechos de 
 * autor, para más información: https://github.com/AlexBollen/POS_Clothes
 */
package org.wass.models.utilities;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * Clase utilidades <code>Cipher</code> encargado de proprocionar 
 * métodos de cifrado seguro.
 * <p>
 * Para el cifrado de contraseñas, se utiliza la biblioteta BCrypt (https://github.com/patrickfav/bcrypt) 
 * donde <code>Cipher</code> ofrece método que facilita el uso de ello, se 
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
public final class Cipher {
    
    /**
     * Tamaño máximo que soporta el algoritmo de cifrado Blowfish, dicho tamaño 
     * es utilizado por <b>BCrypt</b> para generar hashes.
     */
    public static final int MAX_BLOWFISH  = 72;
    
    /**
     * El costo del algoritmo a utilizar, entre más grande sea el costo más 
     * complicado será el cifrado lo que implica más tiempo de romperlo.
     */
    public static final int COST_BLOWFISH = 10;
    
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
    
    //== ------------------------------------------------------------------- ===
    //==                            CIFRADO - AES
    //== ------------------------------------------------------------------- ===
    public static SecureRandom sr = new SecureRandom();
    public static String encriptar(String clave, byte[] iv, String value) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(iv));

            byte[] encriptado = cipher.doFinal(value.getBytes());
            return DatatypeConverter.printBase64Binary(encriptado);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }
    public static String decriptar(String clave, byte[] iv, String encriptado) {
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, sks, new IvParameterSpec(iv));

            byte[] dec = cipher.doFinal(DatatypeConverter.parseBase64Binary(encriptado));
            return new String(dec);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
}

package com.mycompany.javacipher;

/**
 *
 * @author Gustavo
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encrypt {
    
    public static String encrypt(String key, String initVector, String value){
        try{
            //IvParameterSpec especifica um vetor inicial
            IvParameterSpec ips=new IvParameterSpec(initVector.getBytes("UTF-8"));
            //Classe que especifica um chave secreta independente do provedor, util apenas com chaves secretas que podem ser
            //representadas por arrays de bytes e nao tem parametros de chave associados.
            SecretKeySpec sks=new SecretKeySpec(key.getBytes("UTF-8"), "AES");//recebe por argumentos um array de bytes e o algoritmo usado para a encriptacao
            
            //A classe Cipher prove funcionalidades e cifras para encriptar e decriptar, e a parte central da  
            //Java Cryptographic Extension (JCE).
            //Para criar uma cifra e necessario usar getInstance, o metodo tem como parametros o algoritmo que sera usado
            //e opcionalmente o modo de cifra e esquema de preenchimento
            Cipher c=Cipher.getInstance("AES/CBC/PKCS5PADDING");
            
            //Inicializa a encriptacao com base nos parametros passados
            c.init(Cipher.ENCRYPT_MODE, sks, ips);
            
            //Realiza a operacao sobre os dados com uma operacao unica
            byte encrypted[]=c.doFinal(value.getBytes());
            System.out.println("encrypted string: "+Base64.encodeBase64String(encrypted));
            
            return Base64.encodeBase64String(encrypted);
        }catch(UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    public static String decrypt(String key, String initVector, String encrypted){
        
        try {
            IvParameterSpec ips=new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec sks=new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            
            Cipher c=Cipher.getInstance("AES/CBC/PKCS5PADDING");
            c.init(Cipher.DECRYPT_MODE, sks, ips);
            
            byte original[]=c.doFinal(Base64.decodeBase64(encrypted));
            
            return new String(original);
            
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

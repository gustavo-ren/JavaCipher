package com.mycompany.javacipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Gustavo
 */
public class EncryptTest {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        String key = "Bar12345Bar12345"; // 128 bit key
//        String initVector = "RandomInitVector"; // 16 bytes IV
        
        //SecureRandom e usado para gerar numeroos randomicos criptograficamente seguros.
        //Esses numeros devem estar de acordo com os testes FIPS-140-2, e devem produzir resultados nao deterministicos.
        //O codigo abaixo e usado para recuperar os bits randomicos
        SecureRandom random = new SecureRandom();//gera o objeto SecureRandom
        byte bytes[] = new byte[16];//Cria o vetor de bytes responsavel por receber o random 
        random.nextBytes(bytes);//Metodo usado para preencher o vetor com o resultado gerado 

        //Objeto encoder usado para codificar os dados em base 64 de acordo com s RFC 4648 e RFC 2045
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();

        //Transforma os bytes em String e atribui uma String de 16 bytes para o vetor inicial
        String initVector = encoder.encodeToString(bytes).substring(0, 16);
        System.out.println(initVector);

        //Chamada ao metodo de encriptacao
        String encrypt = Encrypt.encrypt(key, initVector, "House of Targaryen");

        //Chamada ao metodo de decriptacao
        String decrypt = Encrypt.decrypt(key, initVector, encrypt);
        System.out.println(decrypt);

    }
}

package com.test.rsa;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;

/**
 * Created by zhangfan on 2015/3/24.
 */
public class MyTestMain {
    public static void main(String[] args) throws Exception {

        String str = "你好   呵呵88888888888888888888888888888888888888888888888888888888888888888";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

//        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        Key key =  keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,key);

        byte[] bytes = cipher.doFinal(str.getBytes());

        System.out.println(Base64Utils.encode(bytes));

        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        bytes = cipher.doFinal(bytes);

        System.out.println(new String(bytes));


    }
}

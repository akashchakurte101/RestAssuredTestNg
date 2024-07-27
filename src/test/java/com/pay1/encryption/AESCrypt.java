package com.pay1.encryption;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import encryption.Base64;
import org.testng.annotations.Parameters;


public class AESCrypt {

    private final Cipher cipher;
    private final SecretKeySpec key;
    private AlgorithmParameterSpec spec;


    public AESCrypt(String environment) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        if ("dev".equals(environment)) {
            digest.update("ARMjU1M674OUZ4Qz".getBytes(StandardCharsets.UTF_8));
        } else {

            digest.update("A778M5U1MGHUZ4Qz".getBytes(StandardCharsets.UTF_8));
        }
        byte[] keyBytes = new byte[32];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);

        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");//AES/CBC/PKCS5PADDING

        key = new SecretKeySpec(keyBytes, "AES");
        spec = getIV();
    }

    public AlgorithmParameterSpec getIV() {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);
        return ivParameterSpec;
    }

    public String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        String encryptedText = new String(Base64.encode(encrypted, Base64.DEFAULT), StandardCharsets.UTF_8);
        return encryptedText;
    }

    public String decrypt(String cryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = Base64.decode(cryptedText, Base64.DEFAULT);
        byte[] decrypted = cipher.doFinal(bytes);
        String decryptedText = new String(decrypted, StandardCharsets.UTF_8);
        return decryptedText;
    }
}
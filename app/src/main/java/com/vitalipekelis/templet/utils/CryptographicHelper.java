package com.vitalipekelis.templet.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


/**
 * Created by vitali.pekelis on 08/09/2015.
 */
public class CryptographicHelper {

    private static SecretKey mKey;

    private static Cipher mEcipher;
    private static Cipher mDcipher;

    public CryptographicHelper() {
        initEncrDecr();
    }

    private void initEncrDecr() {
        try {
            // generate secret key using DES algorithm
            mKey = KeyGenerator.getInstance("DES").generateKey();

            mEcipher = Cipher.getInstance("DES");
            mDcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            mEcipher.init(Cipher.ENCRYPT_MODE, mKey);

        } catch (GeneralSecurityException e ) {
            e.printStackTrace();
        }
    }

    public String decript(String encripted) {
        // decode with base64 to get bytes
        byte[] dec = Base64.decode(encripted.getBytes(), Base64.DEFAULT);
        byte[] utf8 = new byte[0];
        try {

            utf8 = mDcipher.doFinal(dec);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        try {
            return new String(utf8, "UTF8");               //return result
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String encrypt(String decrypted){
        byte[] enc = new byte[0];//init
        try {
            byte[] utf8 = decrypted.getBytes("UTF8");
            enc = mEcipher.doFinal(utf8);

            // encode to base64
            enc = Base64.encode(enc,Base64.DEFAULT);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return new String(enc);

    }

}

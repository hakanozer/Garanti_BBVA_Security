package com.garanti.utils;

public class MainTink {
    public static void main(String[] args) {

        String key = "RgUkXp2s5v8y/B?E";
        String extraKey = "key123";
        String plainText = "Bugün Günlerden Java";

        String cipherText = TinkEncDec.encrypt(key, plainText, extraKey);
        System.out.println( cipherText );

        String text = TinkEncDec.decrypt(key, cipherText, extraKey);
        System.out.println(text);



    }
}

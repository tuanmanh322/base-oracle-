package com.mockapi.mockapi.util;

public class RandomPassword {
    public static String pwGenerate(){
        String rdpw = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            int index
                    = (int)(rdpw.length()
                    * Math.random());
            sb.append(rdpw.charAt(index));
        }
        return sb.toString();
    }
}

package com.mockapi.mockapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args){
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldpw = "admin";
        String newpw = encoder.encode(oldpw);

        if(encoder.matches(oldpw,newpw)){
            System.out.println(newpw);
            System.out.println("Đúng");
        }else{
            System.out.println("sai");
        }

    }


}

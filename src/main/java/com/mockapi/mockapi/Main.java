package com.mockapi.mockapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args){
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldpw = "3djqAjrY";
        String newpw = "$2a$10$HIiYbijcXGxxr1yX8q3xOulUuKpLQoRsZYkG5gTp7yAN5QV7cLpt2";

        if(encoder.matches(oldpw,newpw)){
            System.out.println(newpw);
            System.out.println("Đúng");
        }else{
            System.out.println("sai");
        }

    }


}

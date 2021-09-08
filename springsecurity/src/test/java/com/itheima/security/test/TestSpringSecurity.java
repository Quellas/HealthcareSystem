package com.itheima.security.test;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestSpringSecurity {

    @Test
    public void testSpringSecurity(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String s = encoder.encode("abc");
        System.out.println(s);
        String s1 = encoder.encode("abc");
        System.out.println(s1);

         // $2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a
         boolean b = encoder.matches("abc", "$2a$10$l5OaoPkVxV8QzaOS/tbSpOmty4yO9qZHoOZWKFT2J8LGcTjAXIQ8W");
        System.out.println(b);


    }


}

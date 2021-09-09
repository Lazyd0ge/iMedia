package com.lazydog.admin.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;

public class PWDTEST {
    public static void main(String[] args) {
        String admin = BCrypt.hashpw("admin", BCrypt.gensalt());
        System.out.println(admin);
        System.out.println(new Random(5).nextInt(1000));
    }
}

package com.pablodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pablodev")
public class MascotaFelizApplication {

    public static void main(String[] args) {
        SpringApplication.run(MascotaFelizApplication.class, args);
    }

}
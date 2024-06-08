package com.usuario.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.usuario.service.feignClients")
public class UsuarioServiceApplication {

   public static void main(String[] args) {
      SpringApplication.run(UsuarioServiceApplication.class, args);
   }

}

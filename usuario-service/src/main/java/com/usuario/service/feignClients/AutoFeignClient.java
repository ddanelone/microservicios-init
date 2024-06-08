package com.usuario.service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.modelos.Auto;

@FeignClient(name = "auto-service")
public interface AutoFeignClient {

   @PostMapping("/auto")
   public Auto save(@RequestBody Auto auto);

   @GetMapping("/auto/usuario/{usuarioId}")
   List<Auto> getAutos(@PathVariable("usuarioId") int usuarioId);

}

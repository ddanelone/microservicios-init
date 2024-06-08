package com.moto.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicios.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

   @Autowired
   private MotoService motoService;

   @GetMapping
   public ResponseEntity<List<Moto>> listarAutos() {
      List<Moto> motos = motoService.getAll();
      if (motos.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(motos);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Moto> obtenerMotoPorId(@PathVariable("id") int id) {
      Moto nuevaMoto = motoService.getMotoById(id);
      if (nuevaMoto == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(nuevaMoto);
   }

   @GetMapping("/usuario/{usuarioId}")
   public ResponseEntity<List<Moto>> obtenerMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
      List<Moto> motos = motoService.getMotosByUsuarioId(usuarioId);
      if (motos.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(motos);
   }

   @PostMapping
   public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
      return ResponseEntity.ok(motoService.save(moto));
   }

}

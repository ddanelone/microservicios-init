package com.auto.service.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.service.entidades.Auto;
import com.auto.service.servicios.AutoService;

@RestController
@RequestMapping("/auto")
public class AutoController {

   @Autowired
   private AutoService autoService;

   @GetMapping
   public ResponseEntity<List<Auto>> listarAutos() {
      List<Auto> autos = autoService.obtenerAutos();
      if (autos.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(autos);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Auto> obtenerAutoPorId(@PathVariable("id") int id) {
      Auto nuevoAuto = autoService.obtenerAutoPorId(id);
      if (nuevoAuto == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(nuevoAuto);
   }

   @GetMapping("/usuario/{usuarioId}")
   public ResponseEntity<List<Auto>> obtenerAutosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
      List<Auto> autos = autoService.obtenerAutosPorUsuarioId(usuarioId);
      if (autos.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(autos);
   }

   @PostMapping
   public ResponseEntity<Auto> guardarAuto(@RequestBody Auto auto) {
      return ResponseEntity.ok(autoService.guardarAuto(auto));
   }

}

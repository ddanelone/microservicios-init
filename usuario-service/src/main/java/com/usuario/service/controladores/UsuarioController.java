package com.usuario.service.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicios.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;

   @GetMapping
   public ResponseEntity<List<Usuario>> listarUsuarios() {
      List<Usuario> usuarios = usuarioService.getAll();
      if (usuarios.isEmpty()) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(usuarios);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
      Usuario usuario = usuarioService.getById(id);
      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(usuario);
   }

   @PostMapping
   public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
      Usuario nuevoUsuario = usuarioService.save(usuario);
      return ResponseEntity.ok(nuevoUsuario);
   }

   @CircuitBreaker(name = "autosCB", fallbackMethod = "fallbackGetAutos")
   @GetMapping("autos/{usuarioId}")
   public ResponseEntity listarAutos(@PathVariable("usuarioId") int usuarioId) {

      Usuario usuario = usuarioService.getById(usuarioId);
      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(usuarioService.getAutos(usuarioId));
   }

   @CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackGetMotos")
   @GetMapping("motos/{usuarioId}")
   public ResponseEntity listarMotos(@PathVariable("usuarioId") int usuarioId) {
      Usuario usuario = usuarioService.getById(usuarioId);
      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(usuarioService.getMotos(usuarioId));
   }

   @CircuitBreaker(name = "autosCB", fallbackMethod = "fallbackSaveAuto")
   @PostMapping("/auto/{usuarioId}")
   public ResponseEntity<Auto> guardarAuto(@PathVariable("usuarioId") int usuarioId, @RequestBody Auto auto) {
      Auto nuevoAuto = usuarioService.saveAuto(usuarioId, auto);
      return ResponseEntity.ok(nuevoAuto);
   }

   @CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackSaveMoto")
   @PostMapping("/moto/{usuarioId}")
   public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
      Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
      return ResponseEntity.ok(nuevaMoto);
   }

   @CircuitBreaker(name = "todosCB", fallbackMethod = "fallbackGetTodos")
   @GetMapping("/todos/{usuarioId}")
   public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId) {
      return usuarioService.getUsuariosAndVehiculos(usuarioId);
   }

   private ResponseEntity<List<Auto>> fallbackGetAutos(@PathVariable("usuarioId") int usuarioId, Throwable e) {
      return new ResponseEntity("No podemos recuperar los autos del usuario con id: " + usuarioId, HttpStatus.OK);
   }

   private ResponseEntity<List<Moto>> fallbackGetMotos(@PathVariable("usuarioId") int usuarioId, Throwable e) {
      return new ResponseEntity("No podemos recuperar las motos del usuario con id: " + usuarioId, HttpStatus.OK);
   }

   private ResponseEntity<Auto> fallbackSaveAuto(@PathVariable("usuarioId") int usuarioId, @RequestBody Auto auto,
         Throwable e) {
      return new ResponseEntity("El usuario fracasó como pobre con su auto.", HttpStatus.OK);
   }

   private ResponseEntity<Moto> fallbackSaveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto,
         Throwable e) {
      return new ResponseEntity("El usuario también fracasó como pobre respecto de las motos",
            HttpStatus.OK);
   }

   private ResponseEntity<Map<String, Object>> fallbackGetTodos(@PathVariable("usuarioId") int usuarioId, Throwable e) {
      return new ResponseEntity("No se puede recuperar los vehículos del usuario con id: " + usuarioId,
            HttpStatus.OK);
   }

}

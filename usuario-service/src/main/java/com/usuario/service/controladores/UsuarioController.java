package com.usuario.service.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicios.UsuarioService;
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

   @GetMapping("autos/{usuarioId}")
   public ResponseEntity listarAutos(@PathVariable("usuarioId") int usuarioId) {

      Usuario usuario = usuarioService.getById(usuarioId);
      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(usuarioService.getAutos(usuarioId));
   }

   @GetMapping("motos/{usuarioId}")
   public ResponseEntity listarMotos(@PathVariable("usuarioId") int usuarioId) {
      Usuario usuario = usuarioService.getById(usuarioId);
      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(usuarioService.getMotos(usuarioId));
   }

   @PostMapping("/auto/{usuarioId}")
   public ResponseEntity<Auto> guardarAuto(@PathVariable("usuarioId") int usuarioId, @RequestBody Auto auto) {
      Auto nuevoAuto = usuarioService.saveAuto(usuarioId, auto);
      return ResponseEntity.ok(nuevoAuto);
   }

   @PostMapping("/moto/{usuarioId}")
   public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
      Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
      return ResponseEntity.ok(nuevaMoto);
   }

   @GetMapping("/todos/{usuarioId}")
   public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId) {
      return usuarioService.getUsuariosAndVehiculos(usuarioId);
   }
}

package com.usuario.service.servicios;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feignClients.AutoFeignClient;
import com.usuario.service.feignClients.MotoFeignClient;
import com.usuario.service.modelos.Auto;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private AutoFeignClient autoFeignClient;

   @Autowired
   private MotoFeignClient motoFeignClient;

   public List<Auto> getAutos(int usuarioId) {
      return restTemplate.getForObject("http://localhost:8002/auto/usuario/" + usuarioId, List.class);
   }

   public List<Moto> getMotos(int usuarioId) {
      return restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
   }

   public Auto saveAuto(int usuarioId, Auto auto) {
      auto.setUsuarioId(usuarioId);
      return autoFeignClient.save(auto);
   }

   public Moto saveMoto(int usuarioId, Moto moto) {
      moto.setUsuarioId(usuarioId);
      return motoFeignClient.save(moto);
   }

   public List<Usuario> getAll() {
      return usuarioRepository.findAll();
   }

   public Usuario getById(int id) {
      return usuarioRepository.findById(id).orElse(null);
   }

   public Usuario save(Usuario usuario) {
      return usuarioRepository.save(usuario);
   }

   public ResponseEntity<Map<String, Object>> getUsuariosAndVehiculos(int usuarioId) {
      Map<String, Object> result = new HashMap<>();

      Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
      if (!usuarioOpt.isPresent()) {
         result.put("Error", "Usuario no encontrado");
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
      }

      Usuario usuario = usuarioOpt.get();
      System.out.println("Usuario encontrado: " + usuario);
      result.put("usuario", usuario);

      List<Auto> autos = autoFeignClient.getAutos(usuarioId);
      if (autos == null || autos.isEmpty()) {
         result.put("autos", Collections.emptyList());
      } else {
         result.put("autos", autos);
      }

      List<Moto> motos = motoFeignClient.getMotos(usuarioId);
      if (motos == null || motos.isEmpty()) {
         result.put("motos", Collections.emptyList());
      } else {
         result.put("motos", motos);
      }

      return ResponseEntity.ok(result);
   }
}

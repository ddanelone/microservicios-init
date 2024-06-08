package com.auto.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.service.entidades.Auto;
import com.auto.service.repositorios.AutoRepository;

@Service
public class AutoService {

   @Autowired
   private AutoRepository autoRepository;

   public List<Auto> obtenerAutos() {
      return autoRepository.findAll();
   }

   public Auto obtenerAutoPorId(int id) {
      return autoRepository.findById(id).orElse(null);
   }

   public Auto guardarAuto(Auto auto) {
      return autoRepository.save(auto);
   }

   public List<Auto> obtenerAutosPorUsuarioId(int usuarioId) {
      return autoRepository.findByUsuarioId(usuarioId);
   }

}

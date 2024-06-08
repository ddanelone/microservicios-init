package com.moto.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorios.MotoRepository;

@Service
public class MotoService {

   @Autowired
   private MotoRepository motoRepository;

   public List<Moto> getMotosByUsuarioId(int usuarioId) {
      return motoRepository.findByUsuarioId(usuarioId);
   }

   public Moto save(Moto moto) {
      return motoRepository.save(moto);
   }

   public Moto getMotoById(int motoId) {
      return motoRepository.findById(motoId).orElse(null);
   }

   public List<Moto> getAll() {
      return motoRepository.findAll();
   }
}

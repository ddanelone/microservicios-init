package com.moto.service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Moto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String marca;
   private String modelo;
   private int usuarioId;

}

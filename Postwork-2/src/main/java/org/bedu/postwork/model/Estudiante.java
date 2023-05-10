package org.bedu.postwork.model;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name="estudiante")
public class Estudiante {

   public Estudiante() {
   }

   public Estudiante(String nombreCompleto ) {
      this.nombreCompleto = nombreCompleto;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column( name="nombre_completo")
   private String nombreCompleto;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombreCompleto() {
      return nombreCompleto;
   }

   public void setNombreCompleto(String nombreCompleto) {
      this.nombreCompleto = nombreCompleto;
   }

}

package org.bedu.postwork.model;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name="curso")
public class Curso {

   public Curso() {
   }

   public Curso(
      Long id_materia,
      String ciclo,
      Map<Long,Integer> calificaciones
   ) {
      this.id_materia = id_materia;
      this.ciclo = ciclo;
      this.calificaciones = calificaciones;
   }

   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;

   private Long id_materia;

   private String ciclo;

   @ElementCollection( fetch = FetchType.EAGER )
   @CollectionTable(name = "curso_estudiante", joinColumns = {@JoinColumn(name = "id_curso", referencedColumnName = "id")})
   @MapKeyColumn(name="id_estudiante")
   @Column(name="calificacion")
   private Map<Long,Integer> calificaciones;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId_materia() {
      return id_materia;
   }

   public void setId_materia(Long id_materia) {
      this.id_materia = id_materia;
   }

   public String getCiclo() {
      return ciclo;
   }

   public void setCiclo(String ciclo) {
      this.ciclo = ciclo;
   }

   public Map<Long, Integer> getCalificaciones() {
      return calificaciones;
   }

   public void setCalificaciones(Map<Long,Integer> calificaciones) {
      this.calificaciones = calificaciones;
   }

}
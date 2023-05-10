package org.bedu.postwork.multithreading;

import org.bedu.postwork.model.Curso;
import org.bedu.postwork.model.Materia;
import org.bedu.postwork.persistence.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CalculadorPromedioCurso implements Runnable {

   private List<Integer> calificaciones;
   private String nombreMateria;

   public CalculadorPromedioCurso(
      List<Integer> calificaciones,
      String nombreMateria
   ) {
      this.calificaciones = calificaciones;
      this.nombreMateria = nombreMateria;
   }

   @Override
   public void run() {

      double promedio = calificaciones.
         stream().
         mapToDouble( num -> (double) num ).
         average().
         orElse( 0.0 );

      System.out.format( "\nPromedio curso %s: %2.2f", nombreMateria, promedio );
   }

}

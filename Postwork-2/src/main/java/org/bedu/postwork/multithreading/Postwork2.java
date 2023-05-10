package org.bedu.postwork.multithreading;

import org.bedu.postwork.model.Curso;
import org.bedu.postwork.model.Estudiante;
import org.bedu.postwork.model.Materia;
import org.bedu.postwork.persistence.CursoRepository;
import org.bedu.postwork.persistence.EstudianteRepository;
import org.bedu.postwork.persistence.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.bedu.postwork.persistence"})
@ComponentScan({"org.bedu.postwork.persistence"})
@EntityScan("org.bedu.postwork.model")
public class Postwork2 implements CommandLineRunner {

   private final EstudianteRepository estudianteRepository;
   private final MateriaRepository materiaRepository;
   private final CursoRepository cursoRepository;

   @Autowired
   public Postwork2(
      EstudianteRepository estudianteRepository,
      MateriaRepository materiaRepository,
      CursoRepository cursoRepository
   ) {
      this.estudianteRepository = estudianteRepository;
      this.materiaRepository = materiaRepository;
      this.cursoRepository = cursoRepository;
   }

   public static void main( String[] args) {
      SpringApplication.run(Postwork2.class, args);
   }

   @Override
   public void run( String... args) throws Exception {

      cursoRepository.deleteAll();
      materiaRepository.deleteAll();
      estudianteRepository.deleteAll();

      crea_empleados();
      crea_materias();
      crea_cursos();

   }

   private void crea_empleados() {

      List<String> nombres = new ArrayList<String>(Arrays.asList(
         "Arantxa Sanchez Vicario",
         "Martina Navratilova",
         "Steffi Graf",
         "Martina Hingis",
         "Leonardo Lavalle",
         "Boris Becker",
         "Gabriela Sabatini",
         "Monica Seles",
         "Andre Agassi",
         "Pete Sampras",
         "Joan Jett",
         "Pat Benatar",
         "Robert Plan",
         "David Gilmour",
         "Janis Joplin",
         "Lita For",
         "Eric Burdon",
         "Alanis Morriset",
         "Avril Lavigne",
         "Suzanne Vega"
         )
      );

      nombres.
         stream().
         forEach( nombre -> crea_empleado( nombre ) );

   }

   private void crea_empleado( String nombre ) {
      Estudiante estudiante = new Estudiante( nombre );
      estudianteRepository.save( estudiante );
   }

   private void crea_materias() {

      List<String> nombres = new ArrayList<String>(Arrays.asList(
         "Estructura de datos",
         "Bases de datos",
         "Automatas finitos",
         "Ingenieria de software"
         )
      );

      nombres.
         stream().
         forEach( nombre -> crea_materia( nombre ) );

   }

   private void crea_materia( String nombre ) {
      Materia materia = new Materia( nombre );
      materiaRepository.save( materia );
   }

   private void crea_cursos() {

      for ( Materia materia : materiaRepository.findAll() ) {
         crea_curso( materia );
      }


   }

   private void crea_curso( Materia materia ) {

      Map<Long,Integer> calificaciones = new HashMap<>();

      for ( Estudiante estudiante : estudianteRepository.findAll() ) {
         calificaciones.put( estudiante.getId(), calificacionAleatoria() );
      }

      Curso curso = new Curso( materia.getId(), "2022", calificaciones );
      cursoRepository.save( curso );
   }

   private int calificacionAleatoria() {

      final int max = 10;
      final int min = 1;
      final int range = max - min + 1;

      //double random = Math.random();

      // generate random numbers within min to max
      int rand = (int) ( Math.random() * range ) + min;

      return rand;
   }

}

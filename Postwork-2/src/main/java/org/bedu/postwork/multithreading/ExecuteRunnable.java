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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.bedu.postwork.persistence"})
@ComponentScan({"org.bedu.postwork.persistence"})
@EntityScan("org.bedu.postwork.model")
public class ExecuteRunnable implements  CommandLineRunner{

   private final CursoRepository cursoRepository;
   private final MateriaRepository materiaRepository;

   @Autowired
   public ExecuteRunnable(
      CursoRepository cursoRepository,
      MateriaRepository materiaRepository
   ) {
      this.cursoRepository = cursoRepository;
      this.materiaRepository = materiaRepository;
   }

   public static void main( String[] args) {
      SpringApplication.run(ExecuteRunnable.class, args);
   }

   @Override
   public void run( String... args) throws Exception {

      ExecutorService pool = Executors.newCachedThreadPool(); // creamos un pool preconfigurado

      for ( Curso curso : cursoRepository.findAll() ) {
         String nombreMateria = materiaRepository.findById( curso.getId_materia() ).get().getNombre();
         List<Integer> calificaciones = curso.getCalificaciones().values().stream().collect(Collectors.toList());
         pool.execute( new CalculadorPromedioCurso( calificaciones, nombreMateria ) ); // creamos cada hilo y lo ejecutamos
      }

      System.out.println("\n\nEstado del pool antes de apagar: isShutdown()=" + pool.isShutdown() + ", isTerminated()=" + pool.isTerminated());
      pool.shutdown(); // Apagamos el pool para que no pueda recibir nuevos hilos

      try {
         long tiempoLimiteMs = 100;
         System.out.println("Esperando que los hilos finalicen en " + tiempoLimiteMs + "ms...");

         boolean terminaron = pool.awaitTermination(tiempoLimiteMs, TimeUnit.MILLISECONDS); //Esperamos a los hilos por 100ms, retorna true si acabaron antes o hasta los 100ms o false si fueron interrumpidos
         System.out.println("¿Terminaron nuestros hilos? " +  terminaron);

         if(!terminaron){
            System.out.println("Llamando a shutdownNow()...");
            List<Runnable> pendientes = pool.shutdownNow(); //Termina los hilos que se estén ejecutando y retorna una lista de hilos pendientes a ejecutarse
            System.out.println(pendientes.size() + " hilos pendientes");
            terminaron = pool.awaitTermination(tiempoLimiteMs, TimeUnit.MILLISECONDS); //esperando otros 100ms a que terminen nuestros hilos

            if(!terminaron){
               System.out.println("Aún hay hilos pendientes");
            }
            System.out.println("Saliendo de main");
         }

      } catch (InterruptedException e) {
         e.printStackTrace();
      }

   }

}


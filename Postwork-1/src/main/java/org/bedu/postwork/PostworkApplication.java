package org.bedu.postwork;

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

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PostworkApplication implements CommandLineRunner {

	private final EstudianteRepository estudianteRepository;
	private final MateriaRepository materiaRepository;
	private final CursoRepository cursoRepository;

	@Autowired
	public PostworkApplication(
		EstudianteRepository estudianteRepository,
		MateriaRepository materiaRepository,
		CursoRepository cursoRepository
	) {
		this.estudianteRepository = estudianteRepository;
		this.materiaRepository = materiaRepository;
		this.cursoRepository = cursoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PostworkApplication.class, args);
	}

	@Override
	public void run( String... args) throws Exception {

		Estudiante estudiante = new Estudiante( "Steve Jobs");
		estudiante.setId( estudianteRepository.save( estudiante ).getId() );

		Estudiante estudiante2 = new Estudiante( "Linus Torvalds");
		estudiante2.setId( estudianteRepository.save( estudiante2 ).getId() );

		Materia materia = new Materia( "Data Structures" );
		materia.setId( materiaRepository.save( materia ).getId() );

		Map<Long,Integer> calificaciones = new HashMap<>();
		calificaciones.put( estudiante.getId(), 9 );
		calificaciones.put( estudiante2.getId(), 10 );
		Curso curso = new Curso( materia.getId(), "2022", calificaciones );

		cursoRepository.save( curso );

	}

}

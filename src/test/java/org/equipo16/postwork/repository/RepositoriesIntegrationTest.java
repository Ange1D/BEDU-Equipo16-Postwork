package org.equipo16.postwork.repository;

import org.equipo16.postwork.model.Curso;
import org.equipo16.postwork.model.Estudiante;
import org.equipo16.postwork.model.Materia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.postwork.javase2project")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoriesIntegrationTest {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @BeforeAll
    void cleanDatabases(){
        cursoRepository.deleteAll();
        materiaRepository.deleteAll();
        estudianteRepository.deleteAll();
    }

    @Test
    @DisplayName("Prueba de humo")
    void smokeTest(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombreCompleto("Estudiante Uno");
        estudianteRepository.save(estudiante);

        Materia materia = new Materia();
        materia.setNombre("Materia");
        materiaRepository.save(materia);

        Curso curso = new Curso();
        curso.setCiclo("2030");
        curso.setMateria(materia);
        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante, Curso.NO_CALIFICADO);
        curso.setCalificaciones(calificaciones);
        cursoRepository.save(curso);

        assertNotNull(estudiante.getId());
        assertNotNull(materia.getId());
        assertNotNull(curso.getId());
    }

}